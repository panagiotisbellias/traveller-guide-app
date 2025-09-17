package com.bellias.storage;

import com.bellias.travellerguide.Traveller;

import java.util.ArrayList;

public interface DataStore {

    void save(String key, String value);
    String load(String key);
    void delete(String key);
    void saveTravellers(String key, ArrayList<Traveller> travellers);
    ArrayList<Traveller> loadTravellers(String key);

}
