package com.bellias.opendata.weather;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"temp", "feels_like", "temp_min", "temp_max", "pressure", "humidity"})

public class Main {

    @JsonProperty("temp")
    private Double temp;

    @JsonProperty("feels_like")
    private Double feelsLike;

    @JsonProperty("temp_min")
    private Double tempMin;

    @JsonProperty("temp_max")
    private Double tempMax;

    @JsonProperty("pressure")
    private Integer pressure;

    @JsonProperty("humidity")
    private Integer humidity;

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * No-args constructor for serialization/deserialization purposes.
     */
    public Main() {}

    /**
     * Constructor with all known fields.
     *
     * @param temp current temperature
     * @param feelsLike perceived temperature
     * @param tempMin minimum temperature
     * @param tempMax maximum temperature
     * @param pressure atmospheric pressure
     * @param humidity humidity percentage
     */
    public Main(
            Double temp,
            Double feelsLike,
            Double tempMin,
            Double tempMax,
            Integer pressure,
            Integer humidity) {
        super();
        this.temp = temp;
        this.feelsLike = feelsLike;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    /**
     * Gets the current temperature.
     *
     * @return the temperature
     */
    @JsonProperty("temp")
    public Double getTemp() {
        return temp;
    }

    /**
     * Sets the current temperature.
     *
     * @param temp the temperature to set
     */
    @JsonProperty("temp")
    public void setTemp(Double temp) {
        this.temp = temp;
    }

    /**
     * Gets the perceived (feels like) temperature.
     *
     * @return the feelsLike temperature
     */
    @JsonProperty("feels_like")
    public Double getFeelsLike() {
        return feelsLike;
    }

    /**
     * Sets the perceived (feels like) temperature.
     *
     * @param feelsLike the feelsLike temperature to set
     */
    @JsonProperty("feels_like")
    public void setFeelsLike(Double feelsLike) {
        this.feelsLike = feelsLike;
    }

    /**
     * Gets the minimum temperature.
     *
     * @return the minimum temperature
     */
    @JsonProperty("temp_min")
    public Double getTempMin() {
        return tempMin;
    }

    /**
     * Sets the minimum temperature.
     *
     * @param tempMin the minimum temperature to set
     */
    @JsonProperty("temp_min")
    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    /**
     * Gets the maximum temperature.
     *
     * @return the maximum temperature
     */
    @JsonProperty("temp_max")
    public Double getTempMax() {
        return tempMax;
    }

    /**
     * Sets the maximum temperature.
     *
     * @param tempMax the maximum temperature to set
     */
    @JsonProperty("temp_max")
    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

    /**
     * Gets the atmospheric pressure.
     *
     * @return the pressure
     */
    @JsonProperty("pressure")
    public Integer getPressure() {
        return pressure;
    }

    /**
     * Sets the atmospheric pressure.
     *
     * @param pressure the pressure to set
     */
    @JsonProperty("pressure")
    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    /**
     * Gets the humidity percentage.
     *
     * @return the humidity
     */
    @JsonProperty("humidity")
    public Integer getHumidity() {
        return humidity;
    }

    /**
     * Sets the humidity percentage.
     *
     * @param humidity the humidity to set
     */
    @JsonProperty("humidity")
    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    /**
     * Returns a map of additional properties that are not explicitly defined in this class.
     *
     * @return a map of additional unknown properties
     */
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    /**
     * Adds a property to this object. Used during deserialization for unknown fields.
     *
     * @param name the property name
     * @param value the property value
     */
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
