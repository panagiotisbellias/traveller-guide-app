package com.bellias.storage;

import com.bellias.exception.DataStoreException;
import com.bellias.travellerguide.Traveller;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileDataStore implements DataStore {

    private final Path basePath;
    private final ObjectMapper mapper = new ObjectMapper();
    private final boolean singleFileMode;

    public FileDataStore(Path basePath) {
        this.basePath = basePath;
        this.singleFileMode = !Files.isDirectory(basePath) && basePath.toString().endsWith(".txt");
    }

    @Override
    public void save(String key, String value) {
        try {
            if (singleFileMode) {
                Files.createDirectories(basePath.getParent() != null ? basePath.getParent() : Path.of("."));
                Files.writeString(basePath, value);
            } else {
                Files.createDirectories(basePath);
                Files.writeString(basePath.resolve(key + ".txt"), value);
            }
        } catch (IOException e) {
            throw new DataStoreException("Failed to save key '" + key + "' to file store", e);
        }
    }

    @Override
    public String load(String key) {
        try {
            if (singleFileMode) {
                return Files.readString(basePath);
            } else {
                return Files.readString(basePath.resolve(key + ".txt"));
            }
        } catch (IOException e) {
            throw new DataStoreException("Failed to load key '" + key + "' from file store", e);
        }
    }

    @Override
    public void delete(String key) {
        try {
            if (singleFileMode) {
                Files.deleteIfExists(basePath);
            } else {
                Files.deleteIfExists(basePath.resolve(key + ".txt"));
            }
        } catch (IOException e) {
            throw new DataStoreException("Failed to delete key '" + key + "' from file store", e);
        }
    }

    @Override
    public ArrayList<Traveller> saveTravellers(String key, ArrayList<Traveller> travellers) {
        try {
            String json = mapper.writeValueAsString(travellers);
            if (singleFileMode) {
                Files.createDirectories(basePath.getParent() != null ? basePath.getParent() : Path.of("."));
                Files.writeString(basePath, json);
            } else {
                // TODO In multi-file mode, you could save per key or just one file per list
            }
            Traveller.setTravellersNumber(travellers.size());
            return travellers;
        } catch (IOException e) {
            System.err.println("Failed to save travellers: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<Traveller> loadTravellers(String key) {
        try {
            String json;
            if (singleFileMode) {
                json = Files.readString(basePath);
            } else {
                // handle multi-file mode if needed
                return new ArrayList<>();
            }
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Traveller.class));
        } catch (IOException e) {
            throw new DataStoreException("Failed to load travellers", e);
        }
    }

}
