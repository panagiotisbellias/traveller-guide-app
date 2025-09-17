package com.bellias.storage;

import com.bellias.config.AppProperties;

import java.nio.file.Path;

public class DataStoreFactory {

    public static DataStore create() {
        String backend = AppProperties.getBackend();
        String basePath = AppProperties.getBasePath();

        return switch (backend.toLowerCase()) {
            case "file" -> new FileDataStore(Path.of(basePath));
            case "memory" -> new InMemoryDataStore();
            default -> throw new IllegalArgumentException("Unsupported backend: " + backend);
        };
    }
}
