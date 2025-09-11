package traveller.guide;

import exception.WikipediaNoArticleException;
import gui.GUI;
import java.io.IOException;
import java.util.Objects;
import open.data.rest.*;
import weather.OpenWeatherMap;

/** The Construction of a class that represents a city.
* @since 31-05-2020
* @version 1.4
* @author it21871, it21846 */
public class City {
    
    //City's characteristics
    private String cityName, cityCountry, cityData, weather;
    private double lat, lon;
    
    private WeatherThread weatherT;
    private WikiThread wikiT;

    //============================================================City()============================================================
    /** The constructor initializes the fields which describe the city with certain values.
     * @param cityName the name of the city.
     * @param cityCountry the first two-three letters of city's country.
     * @param cityData the information which describe the city (bars, museums, etc.).
     * @param weather the city's weather.
     * @param lat the lattitude of the city.
     * @param lon the longtitude of the city.
     */
    //==============================================================================================================================
    public City(String cityName, String cityCountry, String cityData, String weather, double lat, double lon) {
    	this.cityName = cityName;
    	this.cityCountry = cityCountry;
        this.cityData = cityData;
        this.weather = weather;
        this.lat = lat;
        this.lon = lon;
    }
    //========================================================End of City()=========================================================
    
    //============================================================City()============================================================
    /** The constructor initializes the fields which describe the city with empty values.
     */
    //==============================================================================================================================
    public City() {
    	cityName = "";
    	cityCountry = "";
    	cityData = "";
    	weather = "";
    	lat = 0;
    	lon = 0;
    }
    //========================================================End of City()=========================================================

    //============================================================City()============================================================
    /** The constructor initializes the fields which describe the city name with certain values and the rest fields with empty ones.
     * @param cityName the name of the city.
     * @param cityCountry the first two-three letters of city's country.
     */
    //==============================================================================================================================
    public City(String cityName, String cityCountry) {
    	this.cityName = cityName;
    	this.cityCountry = cityCountry;
        cityData = "";
    	weather = "";
    	lat = 0;
    	lon = 0;
    }
    //========================================================End of City()=========================================================
    
    //Getters and setters
    
    /**
     * @return the cityName
     */
    public String getCityName() {
    	return cityName;
    }
    
    /**
     * @param cityName the name of the city.
     */
    public void setCityName(String cityName) {
    	this.cityName = cityName;
    }
    
    /**
     * @return the cityCountry
     */
    public String getCityCountry() {
    	return cityCountry;
    }
    
    /**
     * @param cityCountry the first two-three letters of city's country.
     */
    public void setCityCountry(String cityCountry) {
    	this.cityCountry = cityCountry;
    }
    
    /**
     * @return the cityData
     */
    public String getCityData() {
        return cityData;
    }

    /**
     * @param cityData the information which describe the city (bars, museums, etc.).
     */
    public void setCityData(String cityData) {
        this.cityData = cityData;
    }
    
    /**
     * @return the weather
     */
    public String getWeather() {
        return weather;
    }
    
    /**
     * @param weather the city's weather.
     */
    public void setWeather(String weather) {
        this.weather = weather;
    }
    
    /**
     * @return the lat
     */
    public double getLat() {
        return lat;
    }

    /**
     * @param lat the lattitude of the city.
     */
    public void setLat(double lat) {
        this.lat = lat;
    }
	
    /**
     * @return the lon
     */
    public double getLon() {
        return lon;
    }

    /**
     * @param lon the longtitude of the city.
     */
    public void setLon(double lon) {
	    this.lon = lon;
    }
    
    //=========================================================configureCity()======================================================
    /** The method calls APIs to set object's fields.
     * @param APPID our OpenWeatherMap API id.
     * @return false in case we have to handle exceptions so there is a problem, and true in case of successful execution.
     * @throws exception.WikipediaNoArticleException
     * @throws java.io.IOException
     */
    //==============================================================================================================================
    public boolean configureCity(String APPID) throws WikipediaNoArticleException, IOException {
        
        try{
            weatherT = new WeatherThread("citywt", getCityName(), getCityCountry(), APPID);
            weatherT.start();
            while(weatherT.getOpenWeatherMapObject() == null) 
                System.out.println("Data loading......");
            
            OpenWeatherMap weatherObject = weatherT.getOpenWeatherMapObject();
            
            wikiT = new WikiThread("citywikit", getCityName());
            wikiT.start();
            
            setCityData(wikiT.getWikiData());
            setLat(weatherObject.getCoord().getLat());
            setLon(weatherObject.getCoord().getLon());
            setWeather((weatherObject.getWeather()).get(0).getMain());
            if(getCityData() == null) throw new WikipediaNoArticleException(getCityName()+", "+getCityCountry());
            /*
        }catch(com.sun.jersey.api.client.UniformInterfaceException e){
            System.out.println("The city doesn't exist\n"
                    + "Try again or enter \"END\" to quit the process");
            return false;*/
        }catch(WikipediaNoArticleException e){
            System.out.println(e.getMessage());
        }
        
        return true;
        
    }
    //======================================================End of configureCity()==================================================
    
    //=============================================================equals()=========================================================
    /** The method compares two city objects setting the uniqueness of City objects via city name.
     * @param obj the object which want to compare with the current object.
     * @return the result of comparing the object given with the current object only by city name.
     */
    //==============================================================================================================================
    @Override
    public boolean equals(Object obj) {
        
        // If the object is compared with itself then return true...
        if(obj == this) {
            return true;
	}
		
        /* Check if obj is an instance of City or not 
          "null instanceof [type]" also returns false... */
	if(!(obj instanceof City)) {
            return false;
	}
		
        // typecast obj to City so that we can compare data members...
	City city = (City) obj;
		
	return getCityName().equals(city.getCityName());
        
    }
    //=========================================================End of equals()=====================================================

    //===========================================================hashCode()========================================================
    /** Auto-generated method to calculate object's hashcode according its fields.
     * @return the result of calculating object's hashcode.
     */
    //=============================================================================================================================
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.cityName);
        hash = 13 * hash + Objects.hashCode(this.cityCountry);
        hash = 13 * hash + Objects.hashCode(this.cityData);
        hash = 13 * hash + Objects.hashCode(this.weather);
        hash = 13 * hash + (int) (Double.doubleToLongBits(this.lat) ^ (Double.doubleToLongBits(this.lat) >>> 32));
        hash = 13 * hash + (int) (Double.doubleToLongBits(this.lon) ^ (Double.doubleToLongBits(this.lon) >>> 32));
        return hash;
    }
    //========================================================End of hashCode()====================================================
    
    //====================================================setSuggestedCityToGUI()==================================================
    /** The method sets suggestedCityText JLabel according to the suggested city defined by recommendation algorithms.
     * @param suggestedCity the suggested city that system defined acoording travellers' data.
     */
    //=============================================================================================================================
    public static void setSuggestedCityToGUI(City suggestedCity){
        
        try{
            GUI.setSuggestedCityText(suggestedCity.getCityName() + ", "
                    + suggestedCity.getCityCountry());
        }catch(NullPointerException e){
            System.out.println(e);
        }
        
    }
    //=================================================End of setSuggestedCityToGUI()==============================================
    
}//======================================================End of Class City ======================================================
