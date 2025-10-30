package com.bellias.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The construction of a utility class that loads application properties from
 * the "application.properties" file and provides access methods.
 *
 * <p>Properties are loaded from the file in the classpath and can be overridden
 * by environment variables. If a property is not found, a default value can
 * be provided.
 *
 * <p>All methods are static and the class cannot be instantiated.
 *
 * @author Panagiotis Bellias
 */
public class AppProperties {

    // Properties container
    private static final Properties props = new Properties();

    static {
        try (InputStream in =
                     AppProperties.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (in != null) {
                props.load(in);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load application.properties", e);
        }
    }

    /**
     * Retrieves a property value by key with a default fallback.
     *
     * <p>Property lookup priority:
     * <ol>
     *   <li>Environment variable (converted to upper case with dots replaced by underscores)</li>
     *   <li>application.properties file</li>
     *   <li>Provided default value</li>
     * </ol>
     *
     * @param key the property key to retrieve
     * @param def the default value if the key is not found
     * @return the resolved property value
     */
    public static String get(String key, String def) {
        return System.getenv()
                .getOrDefault(key.toUpperCase().replace('.', '_'), props.getProperty(key, def));
    }

    /**
     * Retrieves the path to the travellers data file.
     *
     * <p>Property key: "storage.travellersFile", default value: "travellers.json".
     *
     * @return the travellers file path
     */
    public static String getTravellersFile() {
        return get("storage.travellersFile", "travellers.json");
    }

    /**
     * Retrieves the path to the popular cities data file.
     *
     * <p>Property key: "storage.popularCitiesFile", default value: "popular_cities.json".
     *
     * @return the popular cities file path
     */
    public static String getPopularCitiesFile() {
        return get("storage.popularCitiesFile", "popular_cities.json");
    }

    /**
     * Retrieves the path to the help documentation file.
     *
     * <p>Property key: "storage.helpFile", default value: "docs/help.txt".
     *
     * @return the help file path
     */
    public static String getHelpFile() {
        return get("storage.helpFile", "docs/help.txt");
    }

}
