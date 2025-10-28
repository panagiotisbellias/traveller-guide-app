package com.bellias.rest;

import com.bellias.opendata.weather.OpenWeatherMap;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.io.IOException;

/**
 * The Construction of a class that uses multithreading for retrieving OpenWeatherMap API.
 *
 * @author Panagiotis Bellias
 */
public class WeatherThread extends Thread {

  private Thread t;
  private final String threadName;
  private OpenDataRest odr;
  private final String city;
  private final String country;
  private final String appid;
  private OpenWeatherMap owm;

  // ======================================================WeatherThread()====================================================
  /**
   * The constructor initializes all the necessary fields with specific values.
   *
   * @param name the name of thread.
   * @param odrCity the searching city's name.
   * @param odrCountry the searching city's country.
   * @param odrAppid our OpenWeatherMap API id.
   */
  // =========================================================================================================================
  public WeatherThread(String name, String odrCity, String odrCountry, String odrAppid) {
    threadName = name;
    city = odrCity;
    country = odrCountry;
    appid = odrAppid;
    System.out.println("Creating " + threadName);
  }

  // ===================================================End of
  // WeatherThread()================================================

  // ===========================================================run()=========================================================
  /**
   * The method is implemented so as to specify the actions that thread will perform when starts
   * running.
   */
  // =========================================================================================================================
  @Override
  public void run() {
    System.out.println("Running " + threadName);
    try {
      odr = new OpenDataRest();
      owm = odr.RetrieveOpenWeatherMap(city, country, appid);
    } catch (JsonParseException e) {
      // TODO Auto-generated catch block
      System.out.println(e);
    } catch (JsonMappingException | IllegalArgumentException e) {
      // TODO Auto-generated catch block
      System.out.println(e);
    }
    // TODO Auto-generated catch block
    catch (IOException e) {
      // TODO Auto-generated catch block
      System.out.println(e);
    }
    System.out.println("Thread " + threadName + " exiting.");
  }

  // =======================================================End of
  // run()======================================================

  // ===========================================================start()=======================================================
  /** The method is implemented so as to specify the conditions that thread will starts running. */
  // =========================================================================================================================
  @Override
  public void start() {
    System.out.println("Starting " + threadName);
    if (t == null) {
      t = new Thread(this, threadName);
      t.start();
    }
  }

  // =====================================================End of
  // start()======================================================

  /**
   * @return the OpenWeatherMapObject
   */
  public OpenWeatherMap getOpenWeatherMapObject() {
    return owm;
  }
} // =====================================================End of Class
  // WeatherThread=================================================
