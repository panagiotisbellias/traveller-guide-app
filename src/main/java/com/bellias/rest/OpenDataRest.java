package com.bellias.rest;

import com.bellias.exception.WikipediaNoArticleException;
import com.bellias.opendata.weather.OpenWeatherMap;
import com.bellias.opendata.wikipedia.MediaWiki;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriBuilder;

import java.io.IOException;

/**
 * Handles data retrieval from OpenWeatherMap API and MediaWiki API.
 * Author: Panagiotis Bellias, John Violos
 */
public class OpenDataRest {

    /* ---------------- OpenWeatherMap ---------------- */

    /**
     * Retrieves weather data from OpenWeatherMap API for a given city and country.
     *
     * @param city the city name
     * @param country the country code
     * @param appid OpenWeatherMap API ID
     * @return OpenWeatherMap object containing weather data
     */
    public OpenWeatherMap retrieveOpenWeatherMap(String city, String country, final String appid)
            throws IOException {

        WebTarget service;
        try (Client client = ClientBuilder.newClient()) {
            service = client.target(
                    UriBuilder.fromUri("http://api.openweathermap.org/data/2.5/weather")
                            .queryParam("q", city + "," + country)
                            .queryParam("APPID", appid)
                            .build()
            );
        }

        String json = service.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, OpenWeatherMap.class);
    }

    /* ---------------- MediaWiki ---------------- */

    /**
     * Retrieves the Wikipedia article for a given city.
     *
     * @param city the city name
     * @return article text from Wikipedia
     */
    public String retrieveWikipedia(String city) throws IOException, WikipediaNoArticleException {

        WebTarget service;
        try (Client client = ClientBuilder.newClient()) {
            service = client.target(
                    UriBuilder.fromUri("https://en.wikipedia.org/w/api.php")
                            .queryParam("action", "query")
                            .queryParam("prop", "extracts")
                            .queryParam("titles", city)
                            .queryParam("format", "json")
                            .queryParam("formatversion", 2)
                            .build()
            );
        }

        String json = service.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();

        if (json.contains("pageid")) {
            MediaWiki mediaWikiObj = mapper.readValue(json, MediaWiki.class);
            return mediaWikiObj.getQuery().getPages().getFirst().getExtract();
        } else {
            throw new WikipediaNoArticleException(city);
        }
    }

    /* ---------------- Utility Methods ---------------- */

    /**
     * Counts the occurrences of a criterion in a Wikipedia article.
     *
     * @param cityArticle the article text
     * @param criterion the search criterion
     * @return number of occurrences
     */
    public static int countCriterionOfCity(String cityArticle, String criterion) {
        cityArticle = cityArticle.toLowerCase();
        int index = cityArticle.indexOf(criterion);
        int count = 0;
        while (index != -1) {
            count++;
            cityArticle = cityArticle.substring(index + 1);
            index = cityArticle.indexOf(criterion);
        }
        return count;
    }
}
