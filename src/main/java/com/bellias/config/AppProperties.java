package com.bellias.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppProperties {

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

  public static String get(String key, String def) {
    // Priority: ENV > application.properties > default
    return System.getenv()
        .getOrDefault(key.toUpperCase().replace('.', '_'), props.getProperty(key, def));
  }

  public static String getTravellersFile() {
    return get("storage.travellersFile", "travellers.json");
  }

  public static String getPopularCitiesFile() {
    return get("storage.popularCitiesFile", "popular_cities.json");
  }

  public static String getHelpFile() {
    return get("storage.helpFile", "docs/help.txt");
  }
}
