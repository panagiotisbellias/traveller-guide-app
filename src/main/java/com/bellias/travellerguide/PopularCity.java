package com.bellias.travellerguide;

import com.bellias.exception.WikipediaNoArticleException;
import com.bellias.rest.OpenDataRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Represents a popular city with a name, country, and popularity score.
 * <p>
 * Popularity can be calculated based on the total number of words in the city's Wikipedia article.
 * </p>
 *
 * @author Panagiotis Bellias
 */
public class PopularCity {

    private static final Logger log = LoggerFactory.getLogger(PopularCity.class);
    /** The name of the city */
    private String name;

    /** The country where the city is located */
    private String country;

    /** Popularity score of the city */
    private int popularity;

    /**
     * Constructs a PopularCity with name, country, and popularity.
     *
     * @param name the name of the city
     * @param country the country of the city
     * @param popularity the popularity score
     */
    public PopularCity(String name, String country, int popularity) {
        this.name = name;
        this.country = country;
        this.popularity = popularity;
    }

    /**
     * Constructs a PopularCity with name and country. Popularity defaults to 0.
     *
     * @param name the name of the city
     * @param country the country of the city
     */
    public PopularCity(String name, String country) {
        this.name = name;
        this.country = country;
    }

    /**
     * Default constructor. Initializes name and country as empty strings and popularity as 0.
     */
    public PopularCity() {
        name = "";
        country = "";
        popularity = 0;
    }

    /**
     * Returns the name of the city.
     *
     * @return the city name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the city.
     *
     * @param name the city name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the country of the city.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country of the city.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Returns the popularity score of the city.
     *
     * @return the popularity score
     */
    public int getPopularity() {
        return popularity;
    }

    /**
     * Sets the popularity score of the city.
     *
     * @param popularity the popularity score
     */
    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    /**
     * Calculates the popularity of the city by retrieving its Wikipedia article and counting
     * the total number of words.
     * <p>
     * If the Wikipedia article cannot be retrieved, the popularity will remain 0.
     * </p>
     */
    public void calculatePopularity() {

        OpenDataRest odr = new OpenDataRest();
        String article = "";
        try {
            article = odr.retrieveWikipedia(name);
        } catch (IOException | WikipediaNoArticleException ex) {
            log.error(String.valueOf(ex));
        }

        popularity = countTotalWords(article);
    }

    /**
     * Counts all words in the given input string.
     *
     * @param str the input string
     * @return the number of words in the string
     * @author John Violos
     */
    public static int countTotalWords(String str) {
        String[] s = str.split(" ");
        return s.length;
    }

    /**
     * Returns a string representation of the PopularCity object in the format:
     * "name, country, popularity".
     *
     * @return string representation of the city
     */
    @Override
    public String toString() {
        return name + ", " + country + ", " + popularity;
    }
}
