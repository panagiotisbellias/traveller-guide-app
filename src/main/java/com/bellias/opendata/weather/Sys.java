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
@JsonPropertyOrder({"type", "id", "country", "sunrise", "sunset"})

public class Sys {

    @JsonProperty("type")
    private Integer type;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("country")
    private String country;

    @JsonProperty("sunrise")
    private Integer sunrise;

    @JsonProperty("sunset")
    private Integer sunset;

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * No-args constructor for serialization/deserialization purposes.
     */
    public Sys() {}

    /**
     * Constructor with all known fields.
     *
     * @param type internal API type identifier
     * @param id internal API ID
     * @param country country code (ISO 3166-1 alpha-2)
     * @param sunrise sunrise time as UNIX timestamp
     * @param sunset sunset time as UNIX timestamp
     */
    public Sys(Integer type, Integer id, String country, Integer sunrise, Integer sunset) {
        super();
        this.type = type;
        this.id = id;
        this.country = country;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    /** Gets the internal API type identifier. */
    @JsonProperty("type")
    public Integer getType() {
        return type;
    }

    /** Sets the internal API type identifier. */
    @JsonProperty("type")
    public void setType(Integer type) {
        this.type = type;
    }

    /** Gets the internal API ID. */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /** Sets the internal API ID. */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /** Gets the country code (ISO 3166-1 alpha-2). */
    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    /** Sets the country code (ISO 3166-1 alpha-2). */
    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    /** Gets the sunrise time as UNIX timestamp. */
    @JsonProperty("sunrise")
    public Integer getSunrise() {
        return sunrise;
    }

    /** Sets the sunrise time as UNIX timestamp. */
    @JsonProperty("sunrise")
    public void setSunrise(Integer sunrise) {
        this.sunrise = sunrise;
    }

    /** Gets the sunset time as UNIX timestamp. */
    @JsonProperty("sunset")
    public Integer getSunset() {
        return sunset;
    }

    /** Sets the sunset time as UNIX timestamp. */
    @JsonProperty("sunset")
    public void setSunset(Integer sunset) {
        this.sunset = sunset;
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
