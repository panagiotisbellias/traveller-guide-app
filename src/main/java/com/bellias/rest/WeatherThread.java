package com.bellias.rest;

import com.bellias.opendata.weather.OpenWeatherMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * A thread class responsible for retrieving weather data from the OpenWeatherMap API.
 *
 * @author Panagiotis Bellias
 */
public class WeatherThread extends Thread {

    private static final Logger log = LoggerFactory.getLogger(WeatherThread.class);
    private Thread t;
    private final String threadName;
    private final String city;
    private final String country;
    private final String appId;
    private OpenWeatherMap owm;

    /**
     * Initializes a new WeatherThread with the given parameters.
     *
     * @param name the thread name
     * @param odrCity the target city
     * @param odrCountry the target country
     * @param odrAppId the OpenWeatherMap API key
     */
    public WeatherThread(String name, String odrCity, String odrCountry, String odrAppId) {
        this.threadName = name;
        this.city = odrCity;
        this.country = odrCountry;
        this.appId = odrAppId;
        System.out.println("Creating " + threadName);
    }

    /**
     * Executes the thread logic for retrieving OpenWeatherMap data.
     */
    @Override
    public void run() {
        System.out.println("Running " + threadName);
        try {
            OpenDataRest odr = new OpenDataRest();
            owm = odr.retrieveOpenWeatherMap(city, country, appId);
        } catch (IllegalArgumentException | IOException e) {
            log.error(String.valueOf(e));
        }
        System.out.println("Thread " + threadName + " exiting.");
    }

    /**
     * Starts the thread if not already started.
     */
    @Override
    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }

    /**
     * Returns the retrieved OpenWeatherMap object.
     *
     * @return the OpenWeatherMap data object
     */
    public OpenWeatherMap getOpenWeatherMapObject() {
        return owm;
    }
}
