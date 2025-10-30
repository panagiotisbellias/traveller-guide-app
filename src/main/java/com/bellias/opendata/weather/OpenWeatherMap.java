package com.bellias.opendata.weather;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "coord",
        "weather",
        "base",
        "main",
        "visibility",
        "wind",
        "clouds",
        "dt",
        "sys",
        "timezone",
        "id",
        "name",
        "cod"
})

public class OpenWeatherMap {

    @JsonProperty("coord")
    private Coord coord;

    @JsonProperty("weather")
    private List<Weather> weather = null;

    @JsonProperty("base")
    private String base;

    @JsonProperty("main")
    private Main main;

    @JsonProperty("visibility")
    private Integer visibility;

    @JsonProperty("wind")
    private Wind wind;

    @JsonProperty("clouds")
    private Clouds clouds;

    @JsonProperty("dt")
    private Integer dt;

    @JsonProperty("sys")
    private Sys sys;

    @JsonProperty("timezone")
    private Integer timezone;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("cod")
    private Integer cod;

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * No-args constructor for serialization/deserialization purposes.
     */
    public OpenWeatherMap() {}

    /**
     * Constructor with all known fields.
     *
     * @param coord coordinates of the location
     * @param weather list of weather conditions
     * @param base internal parameter of API
     * @param main main temperature metrics
     * @param visibility visibility in meters
     * @param wind wind metrics
     * @param clouds cloud metrics
     * @param dt timestamp of data calculation
     * @param sys system info such as country, sunrise, sunset
     * @param timezone timezone offset in seconds
     * @param id location ID
     * @param name location name
     * @param cod internal API code
     */
    public OpenWeatherMap(
            Coord coord,
            List<Weather> weather,
            String base,
            Main main,
            Integer visibility,
            Wind wind,
            Clouds clouds,
            Integer dt,
            Sys sys,
            Integer timezone,
            Integer id,
            String name,
            Integer cod) {
        super();
        this.coord = coord;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.visibility = visibility;
        this.wind = wind;
        this.clouds = clouds;
        this.dt = dt;
        this.sys = sys;
        this.timezone = timezone;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }

    /** Gets the coordinates of the location. */
    @JsonProperty("coord")
    public Coord getCoord() {
        return coord;
    }

    /** Sets the coordinates of the location. */
    @JsonProperty("coord")
    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    /** Gets the list of weather conditions. */
    @JsonProperty("weather")
    public List<Weather> getWeather() {
        return weather;
    }

    /** Sets the list of weather conditions. */
    @JsonProperty("weather")
    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    /** Gets the base internal API value. */
    @JsonProperty("base")
    public String getBase() {
        return base;
    }

    /** Sets the base internal API value. */
    @JsonProperty("base")
    public void setBase(String base) {
        this.base = base;
    }

    /** Gets main temperature metrics. */
    @JsonProperty("main")
    public Main getMain() {
        return main;
    }

    /** Sets main temperature metrics. */
    @JsonProperty("main")
    public void setMain(Main main) {
        this.main = main;
    }

    /** Gets visibility in meters. */
    @JsonProperty("visibility")
    public Integer getVisibility() {
        return visibility;
    }

    /** Sets visibility in meters. */
    @JsonProperty("visibility")
    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    /** Gets wind metrics. */
    @JsonProperty("wind")
    public Wind getWind() {
        return wind;
    }

    /** Sets wind metrics. */
    @JsonProperty("wind")
    public void setWind(Wind wind) {
        this.wind = wind;
    }

    /** Gets cloud metrics. */
    @JsonProperty("clouds")
    public Clouds getClouds() {
        return clouds;
    }

    /** Sets cloud metrics. */
    @JsonProperty("clouds")
    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    /** Gets the timestamp of data calculation. */
    @JsonProperty("dt")
    public Integer getDt() {
        return dt;
    }

    /** Sets the timestamp of data calculation. */
    @JsonProperty("dt")
    public void setDt(Integer dt) {
        this.dt = dt;
    }

    /** Gets system info such as country, sunrise, sunset. */
    @JsonProperty("sys")
    public Sys getSys() {
        return sys;
    }

    /** Sets system info such as country, sunrise, sunset. */
    @JsonProperty("sys")
    public void setSys(Sys sys) {
        this.sys = sys;
    }

    /** Gets timezone offset in seconds. */
    @JsonProperty("timezone")
    public Integer getTimezone() {
        return timezone;
    }

    /** Sets timezone offset in seconds. */
    @JsonProperty("timezone")
    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    /** Gets the location ID. */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /** Sets the location ID. */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /** Gets the location name. */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /** Sets the location name. */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /** Gets the internal API code. */
    @JsonProperty("cod")
    public Integer getCod() {
        return cod;
    }

    /** Sets the internal API code. */
    @JsonProperty("cod")
    public void setCod(Integer cod) {
        this.cod = cod;
    }

    /** Returns additional unknown properties not explicitly defined in this class. */
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    /** Adds an unknown property during deserialization. */
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
