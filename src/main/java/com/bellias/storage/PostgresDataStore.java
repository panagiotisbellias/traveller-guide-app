package com.bellias.storage;

import com.bellias.config.AppProperties;
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

public class PostgresDataStore implements DataStore {

  private final String url;
  private final String user;
  private final String password;
  private final ObjectMapper mapper = new ObjectMapper();

  public PostgresDataStore(String url, String user, String password) {
    this.url = url;
    this.user = user;
    this.password = password;
    initSchema();
  }

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

  private Connection getConnection() throws SQLException {
    return DriverManager.getConnection(url, user, password);
  }

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

  @Override
  public String load(String key) {
    try (Connection conn = getConnection();
        PreparedStatement ps =
            conn.prepareStatement("SELECT data FROM travelguide.travellers WHERE key=?")) {
      ps.setString(1, key);
      try (ResultSet rs = ps.executeQuery()) {
        System.out.println("'" + AppProperties.getHelpFile() + "'");
        System.out.println(rs.next());
        if (rs.next()) {
          return rs.getString("data");
        }
      }
    } catch (SQLException e) {
      throw new DataStoreException("Failed to load key " + key, e);
    }
    return "";
  }

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

  @Override
  public void saveTravellers(String key, ArrayList<Traveller> travellers) {
    try {
      String json = mapper.writeValueAsString(travellers);
      save(key, json);
    } catch (Exception e) {
      throw new DataStoreException("Failed to save travellers", e);
    }
  }

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
