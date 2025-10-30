package com.bellias.storage;

import com.bellias.exception.DataStoreException;
import com.bellias.travellerguide.Traveller;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * {@link DataStore} implementation that stores data in a PostgreSQL database.
 * This class manages storage of generic key-value pairs as well as lists of {@link Traveller} objects.
 * It uses JSON serialization for storing travellers and ensures that the database schema is created on initialization.
 * All operations throw {@link DataStoreException} on failure.
 * Author: Panagiotis Bellias, John Violos
 */
public class PostgresDataStore implements DataStore {

    private final String url;
    private final String user;
    private final String password;
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Constructs a {@link PostgresDataStore} instance with the specified database connection parameters.
     *
     * @param url the JDBC URL of the PostgreSQL database
     * @param user the database user
     * @param password the database password
     */
    public PostgresDataStore(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
        initSchema();
    }

    /**
     * Initializes the database schema if it does not exist.
     * Creates a "travellers" table with columns:
     * <ul>
     *   <li>id: serial primary key</li>
     *   <li>key: text, unique key</li>
     *   <li>data: JSONB, storing the value</li>
     * </ul>
     * @throws DataStoreException if schema creation fails
     */
    private void initSchema() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(
                    """
                            CREATE TABLE IF NOT EXISTS travellers (
                                id SERIAL PRIMARY KEY,
                                key TEXT NOT NULL,
                                data JSONB NOT NULL
                            )
                        """);
        } catch (SQLException e) {
            throw new DataStoreException("Failed to initialize database schema", e);
        }
    }

    /**
     * Opens a new database connection.
     *
     * @return a {@link Connection} to the PostgreSQL database
     * @throws SQLException if the connection fails
     */
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Saves a string value under the specified key in the database.
     * If the key already exists, the value is updated.
     *
     * @param key the key under which to store the value
     * @param value the string value to store
     * @throws DataStoreException if the save operation fails
     */
    @Override
    public void save(String key, String value) {
        try (Connection conn = getConnection();
             PreparedStatement ps =
                     conn.prepareStatement(
                             "INSERT INTO travellers(key, data) VALUES(?, to_json(?::text)) "
                                     + "ON CONFLICT (key) DO UPDATE SET data = EXCLUDED.data")) {
            ps.setString(1, key);
            ps.setString(2, value);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataStoreException("Failed to save key " + key, e);
        }
    }

    /**
     * Loads the string value associated with the given key from the database.
     *
     * @param key the key to load
     * @return the stored string value, or empty string if the key does not exist
     * @throws DataStoreException if the load operation fails
     */
    @Override
    public String load(String key) {
        try (Connection conn = getConnection();
             PreparedStatement ps =
                     conn.prepareStatement("SELECT data FROM travelguide.travellers WHERE key=?")) {
            ps.setString(1, key);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("data");
                }
            }
        } catch (SQLException e) {
            throw new DataStoreException("Failed to load key " + key, e);
        }
        return "";
    }

    /**
     * Deletes the value associated with the given key from the database.
     *
     * @param key the key to delete
     * @throws DataStoreException if the delete operation fails
     */
    @Override
    public void delete(String key) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM travellers WHERE key=?")) {
            ps.setString(1, key);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataStoreException("Failed to delete key " + key, e);
        }
    }

    /**
     * Saves a list of {@link Traveller} objects under the specified key.
     * The list is serialized to JSON before storage.
     *
     * @param key the key to store the travellers under
     * @param travellers the list of travellers to save
     * @throws DataStoreException if the save operation fails
     */
    @Override
    public void saveTravellers(String key, ArrayList<Traveller> travellers) {
        try {
            String json = mapper.writeValueAsString(travellers);
            save(key, json);
        } catch (Exception e) {
            throw new DataStoreException("Failed to save travellers", e);
        }
    }

    /**
     * Loads a list of {@link Traveller} objects stored under the specified key.
     * If no data exists for the key, an empty list is returned.
     *
     * @param key the key to load travellers from
     * @return a list of travellers
     * @throws DataStoreException if the load operation fails
     */
    @Override
    public ArrayList<Traveller> loadTravellers(String key) {
        try {
            String json = load(key);
            if (json == null || json.isEmpty()) return new ArrayList<>();
            return mapper.readValue(
                    json, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Traveller.class));
        } catch (Exception e) {
            throw new DataStoreException("Failed to load travellers", e);
        }
    }
}
