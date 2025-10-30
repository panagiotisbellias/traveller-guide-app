package com.bellias.travellerguide;

import com.bellias.exception.WikipediaNoArticleException;
import com.bellias.gui.GUI;
import com.bellias.opendata.weather.OpenWeatherMap;
import com.bellias.rest.WeatherThread;
import com.bellias.rest.WikiThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * Represents a city with its core characteristics such as name, country, coordinates, weather, and
 * descriptive data retrieved via APIs.
 *
 * <p>Provides functionality to configure a city by fetching weather and Wikipedia information.
 *
 * @author Panagiotis Bellias
 */
public class City {

    private static final Logger log = LoggerFactory.getLogger(City.class);
    private String cityName;
    private String cityCountry;
    private String cityData;
    private String weather;
    private double lat;
    private double lon;

    /**
     * Creates a City with all attributes initialized.
     *
     * @param cityName the city’s name
     * @param cityCountry the country code or abbreviation
     * @param cityData descriptive information about the city (e.g., bars, museums)
     * @param weather the city’s current weather condition
     * @param lat the city’s latitude
     * @param lon the city’s longitude
     */
    public City(String cityName, String cityCountry, String cityData, String weather, double lat, double lon) {
        this.cityName = cityName;
        this.cityCountry = cityCountry;
        this.cityData = cityData;
        this.weather = weather;
        this.lat = lat;
        this.lon = lon;
    }

    /** Creates an empty City with default (zeroed) values. */
    public City() {
        this("", "", "", "", 0, 0);
    }

    /**
     * Creates a City with only name and country defined.
     *
     * @param cityName the city’s name
     * @param cityCountry the country code or abbreviation
     */
    public City(String cityName, String cityCountry) {
        this(cityName, cityCountry, "", "", 0, 0);
    }

    // --- Getters and Setters ---

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCountry() {
        return cityCountry;
    }

    public void setCityCountry(String cityCountry) {
        this.cityCountry = cityCountry;
    }

    public String getCityData() {
        return cityData;
    }

    public void setCityData(String cityData) {
        this.cityData = cityData;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    /**
     * Configures the city by retrieving weather and Wikipedia data from external APIs.
     *
     * @param APPID the OpenWeatherMap API key
     */
    public void configureCity(String APPID) {
        WikiThread wikiT;
        WeatherThread weatherT;
        try {
            weatherT = new WeatherThread("citywt", getCityName(), getCityCountry(), APPID);
            weatherT.start();

            while (weatherT.getOpenWeatherMapObject() == null) {
                System.out.println("Data loading...");
            }

            OpenWeatherMap weatherObject = weatherT.getOpenWeatherMapObject();

            wikiT = new WikiThread("citywikit", getCityName());
            wikiT.start();

            setCityData(wikiT.getWikiData());
            setLat(weatherObject.getCoord().getLat());
            setLon(weatherObject.getCoord().getLon());
            setWeather(weatherObject.getWeather().getFirst().getMain());

            if (getCityData() == null) {
                throw new WikipediaNoArticleException(getCityName() + ", " + getCityCountry());
            }

        } catch (WikipediaNoArticleException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Determines equality based on city name.
     *
     * @param obj the object to compare
     * @return {@code true} if the two City instances share the same name; otherwise {@code false}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof City city)) {
            return false;
        }

        return Objects.equals(getCityName(), city.getCityName());
    }

    /**
     * Computes the hash code for the City instance based on all its fields.
     *
     * @return the computed hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(cityName, cityCountry, cityData, weather, lat, lon);
    }

    /**
     * Updates the GUI to display the suggested city.
     *
     * @param suggestedCity the city suggested by the recommendation algorithm
     */
    public static void setSuggestedCityToGUI(City suggestedCity) {
        try {
            GUI.setSuggestedCityText(suggestedCity.getCityName() + ", " + suggestedCity.getCityCountry());
        } catch (NullPointerException e) {
            log.error("e: ", e);
        }
    }
}
