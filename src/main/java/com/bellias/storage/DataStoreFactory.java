package com.bellias.storage;

import com.bellias.config.AppProperties;

import java.nio.file.Path;

/**
 * Factory class for creating {@link DataStore} instances based on the application configuration.
 * <p>
 * The backend type is determined by the "storage.backend" property in {@link AppProperties}.
 * Supported backends include:
 * <ul>
 *   <li><b>file</b>: Uses {@link FileDataStore} with a path from "storage.basePath"</li>
 *   <li><b>memory</b>: Uses {@link InMemoryDataStore}</li>
 *   <li><b>postgres</b>: Uses {@link PostgresDataStore} with database URL, user, and password
 *       from configuration properties</li>
 * </ul>
 * </p>
 * <p>
 * Example usage:
 * <pre>
 * DataStore store = DataStoreFactory.create();
 * store.save("key", "value");
 * </pre>
 * </p>
 *
 * @author Panagiotis Bellias
 */
public class DataStoreFactory {

    /**
     * Creates and returns a {@link DataStore} instance based on the configured backend.
     * <p>
     * The backend is determined by the "storage.backend" property in {@link AppProperties}.
     * Default is "file". If the backend is not supported, an {@link IllegalArgumentException} is thrown.
     * </p>
     *
     * @return a {@link DataStore} instance corresponding to the configured backend
     * @throws IllegalArgumentException if the configured backend is not supported
     */
    public static DataStore create() {
        String backend = AppProperties.get("storage.backend", "file");

        return switch (backend.toLowerCase()) {
            case "file" -> {
                String path = AppProperties.get("storage.basePath", "travellers.txt");
                yield new FileDataStore(Path.of(path));
            }
            case "memory" -> new InMemoryDataStore();
            case "postgres" -> new PostgresDataStore(
                    AppProperties.get("storage.db.url", "jdbc:postgresql://localhost:5432/travelguide"),
                    AppProperties.get("storage.db.user", "user"),
                    AppProperties.get("storage.db.password", "password"));
            default -> throw new IllegalArgumentException("Unsupported backend: " + backend);
        };
    }
}
