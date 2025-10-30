package com.bellias.storage;

import com.bellias.exception.DataStoreException;
import com.bellias.travellerguide.Traveller;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * {@link DataStore} implementation that persists data to the filesystem.
 * <p>
 * This class supports two modes:
 * <ul>
 *   <li>Single-file mode: If the base path is a file (e.g., "travellers.txt"), all key-value
 *       operations use this file.</li>
 *   <li>Directory mode: If the base path is a directory, each key is resolved to a file inside
 *       this directory.</li>
 * </ul>
 * <p>
 * Traveller objects are serialized/deserialized using Jackson's {@link ObjectMapper}.
 * </p>
 *
 * @author Panagiotis Bellias
 */
public class FileDataStore implements DataStore {

    private final Path basePath;
    private final ObjectMapper mapper = new ObjectMapper();
    private final boolean singleFileMode;

    /**
     * Constructs a new {@link FileDataStore} with the given base path.
     *
     * @param basePath the file or directory path to use for storage
     */
    public FileDataStore(Path basePath) {
        this.basePath = basePath;
        this.singleFileMode = !Files.isDirectory(basePath) && basePath.toString().endsWith(".txt");
    }

    /**
     * Resolves the actual filesystem path for a given key.
     * <p>
     * In single-file mode, the base file is returned. In directory mode, relative keys are
     * appended to the base directory path, while absolute keys are returned as-is.
     * </p>
     *
     * @param key the key to resolve
     * @return the resolved {@link Path} on the filesystem
     */
    private Path resolvePath(String key) {
        if (singleFileMode) return basePath;

        Path keyPath = Path.of(key);

        if (keyPath.isAbsolute()) return keyPath;

        return basePath.resolve(keyPath);
    }

    /**
     * Saves a string value under the given key.
     *
     * @param key the key to store the value under
     * @param value the string value to save
     * @throws DataStoreException if an I/O error occurs
     */
    @Override
    public void save(String key, String value) {
        try {
            Path path = resolvePath(key);
            if (path.getParent() != null) Files.createDirectories(path.getParent());
            Files.writeString(path, value);
        } catch (IOException e) {
            throw new DataStoreException("Failed to save key '" + key + "'", e);
        }
    }

    /**
     * Loads the string value for the given key.
     *
     * @param key the key to load
     * @return the stored string, or an empty string if the file does not exist
     * @throws DataStoreException if an I/O error occurs
     */
    @Override
    public String load(String key) {
        try {
            Path path = resolvePath(key);
            return Files.exists(path) ? Files.readString(path) : "";
        } catch (IOException e) {
            throw new DataStoreException("Failed to load key '" + key + "'", e);
        }
    }

    /**
     * Deletes the value associated with the given key.
     *
     * @param key the key to delete
     * @throws DataStoreException if an I/O error occurs
     */
    @Override
    public void delete(String key) {
        try {
            Path path = resolvePath(key);
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new DataStoreException("Failed to delete key '" + key + "'", e);
        }
    }

    /**
     * Saves a list of {@link Traveller} objects as JSON under the specified key.
     *
     * @param key the key to store the travellers under; defaults to "travellers" if null
     * @param travellers the list of travellers to save
     * @throws DataStoreException if an I/O error occurs during writing
     */
    @Override
    public void saveTravellers(String key, ArrayList<Traveller> travellers) {
        try {
            String json = mapper.writeValueAsString(travellers);
            Path path = resolvePath(key != null ? key : "travellers");
            Files.createDirectories(path.getParent());
            Files.writeString(path, json);
        } catch (IOException e) {
            throw new DataStoreException("Failed to save travellers", e);
        }
    }

    /**
     * Loads a list of {@link Traveller} objects stored under the specified key.
     *
     * @param key the key to load travellers from; defaults to "travellers" if null
     * @return a list of travellers; empty list if no file exists
     * @throws DataStoreException if an I/O error occurs or JSON deserialization fails
     */
    @Override
    public ArrayList<Traveller> loadTravellers(String key) {
        try {
            Path path = resolvePath(key != null ? key : "travellers");
            if (!Files.exists(path)) {
                return new ArrayList<>();
            }
            String json = Files.readString(path);
            return mapper.readValue(
                    json, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Traveller.class));
        } catch (IOException e) {
            throw new DataStoreException("Failed to load travellers", e);
        }
    }
}
