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

  /** Resolve the actual path for a given key */
  private Path resolvePath(String key) {
    if (singleFileMode) return basePath;

    Path keyPath = Path.of(key);

    // If key is absolute, return as-is
    if (keyPath.isAbsolute()) return keyPath;

    // If key is relative, prepend basePath
    return basePath.resolve(keyPath);
  }

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

  @Override
  public String load(String key) {
    try {
      Path path = resolvePath(key);
      return Files.exists(path) ? Files.readString(path) : "";
    } catch (IOException e) {
      throw new DataStoreException("Failed to load key '" + key + "'", e);
    }
  }

  @Override
  public void delete(String key) {
    try {
      Path path = resolvePath(key);
      Files.deleteIfExists(path);
    } catch (IOException e) {
      throw new DataStoreException("Failed to delete key '" + key + "'", e);
    }
  }

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
