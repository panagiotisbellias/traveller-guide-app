package com.bellias.storage;

import java.nio.file.Path;

public class DataStoreFactory {

    public static DataStore create() {
        String path = System.getenv().getOrDefault("APP_STORAGE_PATH", "travellers.txt");
        String backend = System.getenv().getOrDefault("APP_STORAGE_BACKEND", "file");

        return switch (backend.toLowerCase()) {
            case "file" -> new FileDataStore(Path.of(path));
            case "memory" -> new InMemoryDataStore();
            default -> throw new IllegalArgumentException("Unsupported backend: " + backend);
        };
    }

}
