package com.bellias.storage;

import com.bellias.config.AppProperties;

import java.nio.file.Path;

public class DataStoreFactory {

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
                    AppProperties.get("storage.db.password", "password")
            );
            default -> throw new IllegalArgumentException("Unsupported backend: " + backend);
        };
    }

}
