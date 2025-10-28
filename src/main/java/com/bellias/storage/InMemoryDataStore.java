package com.bellias.storage;

import com.bellias.travellerguide.Traveller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryDataStore implements DataStore {

  private final Map<String, String> map = new HashMap<>();
  private final Map<String, ArrayList<Traveller>> travellersMap = new HashMap<>();

  @Override
  public void save(String key, String value) {
    map.put(key, value);
  }

  @Override
  public String load(String key) {
    return map.get(key);
  }

  @Override
  public void delete(String key) {
    map.remove(key);
  }

  @Override
  public void saveTravellers(String key, ArrayList<Traveller> travellers) {
    travellersMap.put(key, travellers);
  }

  @Override
  public ArrayList<Traveller> loadTravellers(String key) {
    return travellersMap.get(key);
  }
}
