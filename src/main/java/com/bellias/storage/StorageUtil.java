package com.bellias.storage;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Utility class for reading from and writing to a {@link DataStore}.
 * <p>
 * This class provides simple methods to read lines from a key (treated as a text file) and
 * write lines to a key while avoiding duplicate entries. It is useful for managing text-based
 * storage in any {@link DataStore} implementation (file, memory, PostgreSQL, etc.).
 * </p>
 * <p>
 * Author: Panagiotis Bellias
 *
 * @param store The underlying data store used for reading and writing.
 */
public record StorageUtil(DataStore store) {

    /**
     * Constructs a {@link StorageUtil} with the specified {@link DataStore}.
     *
     * @param store the data store to read from and write to
     */
    public StorageUtil {
    }

    /**
     * Reads the content of a key from the {@link DataStore} as a list of lines.
     * <p>
     * The content is split using any line break, and each line is added to the returned list.
     * If the key has no content, an empty list is returned.
     * </p>
     *
     * @param key the key (file) from which data will be read
     * @return a list of lines from the file; empty if the key has no content
     */
    public ArrayList<String> read(String key) {

        ArrayList<String> data = new ArrayList<>();
        String content = store.load(key);

        System.out.println(content);
        System.out.println(content.isEmpty());
        if (!content.isEmpty()) {
            String[] lines = content.split("\\R"); // \R matches any line break
            for (String line : lines) {
                System.out.println(line);
                data.add(line);
            }
        }

        return data;
    }

    /**
     * Writes a line of content to the {@link DataStore} key, avoiding duplicates.
     * <p>
     * If the key already contains the content, it will not be added again.
     * Otherwise, the content is appended and the entire set of lines is saved back.
     * </p>
     *
     * @param key     the key (file) to which data will be written
     * @param content the text line to write to the file
     */
    public void write(String key, String content) {

        ArrayList<String> data = new ArrayList<>();
        String existing = store.load(key);
        if (existing != null && !existing.isEmpty()) {
            // split existing lines into ArrayList
            String[] lines = existing.split("\\R"); // \R matches any newline
            data.addAll(Arrays.asList(lines));
        }

        if (!data.contains(content)) {
            data.add(content);
        }

        // save all back
        store.save(key, String.join(System.lineSeparator(), data));
    }

}
