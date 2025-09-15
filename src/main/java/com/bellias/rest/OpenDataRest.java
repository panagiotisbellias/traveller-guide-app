package com.bellias.rest;

import com.bellias.exception.WikipediaNoArticleException;
import com.bellias.opendata.weather.OpenWeatherMap;
import com.bellias.opendata.wikipedia.MediaWiki;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriBuilder;

import java.io.IOException;

/** The Construction of a class that handles the OpenWeatherMap API and the MediaWiki API.
* @since 31-05-2020
* @version 1.4
* @author John Violos */
public class OpenDataRest {
	
    //==================================================RetrieveOpenWeatherMap()================================================
    /** The method handles the OpenWeatherMap API.
     * @param city the city name for which data will be collected.
     * @param country the city's country for which data will be collected.
     * @param appid our OpenWeatherMap API id.
     * @return object that represents all the data from the OpenWeatherMap API for the given city.
     * @throws com.fasterxml.jackson.core.JsonParseException
     * @throws com.fasterxml.jackson.databind.JsonMappingException
     */
    //==========================================================================================================================
	public OpenWeatherMap RetrieveOpenWeatherMap(String city, String country, final String appid)
			throws JsonParseException, JsonMappingException, IOException {

		Client client = ClientBuilder.newClient();
		WebTarget service = client.target(
				UriBuilder.fromUri("http://api.openweathermap.org/data/2.5/weather")
						.queryParam("q", city + "," + country)
						.queryParam("APPID", appid)
						.build()
		);

		String json = service.request(MediaType.APPLICATION_JSON).get(String.class);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, OpenWeatherMap.class);
	}
    //==============================================End of RetrieveOpenWeatherMap()=============================================
	
    //====================================================RetrieveWikipedia()===================================================
    /** The method handles the MediaWiki API.
     * @param city the city for which data will be collected.
     * @return the article containing all the data from the MediaWiki API for the given city.
     * @throws java.io.IOException
     * @throws com.bellias.exception.WikipediaNoArticleException
     */
    //==========================================================================================================================
	public String RetrieveWikipedia(String city) throws IOException, WikipediaNoArticleException {

		Client client = ClientBuilder.newClient();
		WebTarget service = client.target(
				UriBuilder.fromUri("https://en.wikipedia.org/w/api.php")
						.queryParam("action", "query")
						.queryParam("prop", "extracts")
						.queryParam("titles", city)
						.queryParam("format", "json")
						.queryParam("formatversion", 2)
						.build()
		);

		String json = service.request(MediaType.APPLICATION_JSON).get(String.class);
		ObjectMapper mapper = new ObjectMapper();
		String article = "";

		if (json.contains("pageid")) {
			MediaWiki mediaWiki_obj = mapper.readValue(json, MediaWiki.class);
			article = mediaWiki_obj.getQuery().getPages().get(0).getExtract();
		} else {
			throw new WikipediaNoArticleException(city);
		}

		return article;
	}
    //=================================================End of RetrieveWikipedia()================================================

    //======================================================countTotalWords()====================================================
    /** The method counts the number of words contained in a string.
     * @param str the string for which the number of words will be calculated. 
     * @return the number of total words contained in a string.
     */
    //===========================================================================================================================
    public static int countTotalWords(String str) {	
	String s[]=str.split(" ");
	return 	s.length;
    }	
    //==================================================End of countTotalWords()=================================================

    //====================================================countCriterionfCity()==================================================
    /** The method counts how many times a criteria exists in a Wikipedia article.
     * @param cityArticle the Wikipedia article.
     * @param criterion the criteria that will be checked.
     * @return the number of times a criteria exists in the article.
     */
    //===========================================================================================================================
    public static int countCriterionfCity(String cityArticle, String criterion) {
	cityArticle=cityArticle.toLowerCase();
	int index = cityArticle.indexOf(criterion);
	int count = 0;
	while (index != -1) {
	    count++;
	    cityArticle = cityArticle.substring(index + 1);
	    index = cityArticle.indexOf(criterion);
	}
	return count;
    }
    //================================================End of countCriterionfCity()===============================================

}//======================================================End of Class OpenDataRest ======================================================
