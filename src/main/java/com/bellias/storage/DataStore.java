package com.bellias.storage;

import com.bellias.travellerguide.Traveller;

import java.util.ArrayList;

/**
 * Interface defining the basic operations for a data store.
 * <p>
 * Implementations of this interface are responsible for saving, loading, and deleting
 * both generic string values and {@link Traveller} objects. This allows flexibility
 * for storing data in different backends (e.g., files, databases, or in-memory storage).
 * </p>
 *
 * Example usage:
 * <pre>
 * DataStore store = new FileDataStore();
 * store.save("key1", "value1");
 * String value = store.load("key1");
 * </pre>
 *
 * @author Panagiotis Bellias
 */
public interface DataStore {

    /**
     * Saves a string value in the data store under the specified key.
     *
     * @param key the unique key to identify the value
     * @param value the string value to save
     */
    void save(String key, String value);

    /**
     * Loads a string value from the data store using the specified key.
     *
     * @param key the unique key identifying the value
     * @return the string value associated with the key, or null if not found
     */
    String load(String key);

    /**
     * Deletes a value from the data store using the specified key.
     *
     * @param key the unique key identifying the value to delete
     */
    void delete(String key);

    /**
     * Saves a list of {@link Traveller} objects in the data store under the specified key.
     *
     * @param key the unique key to identify the list of travellers
     * @param travellers the list of {@link Traveller} objects to save
     */
    void saveTravellers(String key, ArrayList<Traveller> travellers);

    /**
     * Loads a list of {@link Traveller} objects from the data store using the specified key.
     *
     * @param key the unique key identifying the list of travellers
     * @return the list of {@link Traveller} objects, or an empty list if not found
     */
    ArrayList<Traveller> loadTravellers(String key);
}
