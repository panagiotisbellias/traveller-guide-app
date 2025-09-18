package com.bellias.storage;

import java.util.ArrayList;

/**
 * The Construction of a class that reads from and writes in a text file.
 *
 * @author Panagiotis Bellias
 */
public class StorageUtil {

    private final DataStore store;

    public StorageUtil(DataStore store) {
      this.store = store;
    }

    // ==================================================read()================================================
    /**
     * The method reads from a text file.
     *
     * @param key the file from which data will be taken.
     * @return all the lines of the file.
     */
    // ========================================================================================================
    public ArrayList<String> read(String key) {

      ArrayList<String> data = new ArrayList<>();
      String content = store.load(key);

      System.out.println(content);
      System.out.println(content.isEmpty());
      if (content != null && !content.isEmpty()) {
        String[] lines = content.split("\\R"); // \R matches any line break
        for (String line : lines) {
          System.out.println(line);
          data.add(line);
        }
      }

      return data;
    }

    // ==============================================End of read()=============================================

    // ==================================================write()===============================================
    /**
     * Writes a content line to the datastore file, avoiding duplicates
     *
     * @param key the file to which data will be written.
     * @param content the text that will be written in the file.
     */
    // ========================================================================================================
    public void write(String key, String content) {

      ArrayList<String> data = new ArrayList<>();
      String existing = store.load(key);
      if (existing != null && !existing.isEmpty()) {
        // split existing lines into ArrayList
        String[] lines = existing.split("\\R"); // \R matches any newline
        for (String line : lines) data.add(line);
      }

      if (!data.contains(content)) {
        data.add(content);
      }

      // save all back
      store.save(key, String.join(System.lineSeparator(), data));
    }
    // =============================================End of write()=============================================

} // ======================================================End of Class TxtFile======================================================
