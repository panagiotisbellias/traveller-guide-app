package com.bellias.storage;

import com.bellias.travellerguide.Traveller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * {@link DataStore} implementation that stores data in memory.
 * <p>
 * This class is mainly intended for testing or short-lived data storage since all
 * data is lost when the application terminates.
 * </p>
 *
 * <p>It supports two types of storage:</p>
 * <ul>
 *   <li>String key-value pairs</li>
 *   <li>Lists of {@link Traveller} objects</li>
 * </ul>
 *
 * @author Panagiotis Bellias
 */
public class InMemoryDataStore implements DataStore {

    /** Map to store string key-value pairs */
    private final Map<String, String> map = new HashMap<>();

    /** Map to store travellers lists keyed by string */
    private final Map<String, ArrayList<Traveller>> travellersMap = new HashMap<>();

    /**
     * Saves a string value under the given key.
     *
     * @param key the key to store the value under
     * @param value the string value to save
     */
    @Override
    public void save(String key, String value) {
        map.put(key, value);
    }

    /**
     * Loads the string value associated with the given key.
     *
     * @param key the key to load
     * @return the stored value, or {@code null} if the key does not exist
     */
    @Override
    public String load(String key) {
        return map.get(key);
    }

    /**
     * Deletes the value associated with the given key.
     *
     * @param key the key to delete
     */
    @Override
    public void delete(String key) {
        map.remove(key);
    }

    /**
     * Saves a list of {@link Traveller} objects under the specified key.
     *
     * @param key the key to store the travellers under
     * @param travellers the list of travellers to save
     */
    @Override
    public void saveTravellers(String key, ArrayList<Traveller> travellers) {
        travellersMap.put(key, travellers);
    }

    /**
     * Loads a list of {@link Traveller} objects stored under the specified key.
     *
     * @param key the key to load travellers from
     * @return the list of travellers, or {@code null} if the key does not exist
     */
    @Override
    public ArrayList<Traveller> loadTravellers(String key) {
        return travellersMap.get(key);
    }
}
