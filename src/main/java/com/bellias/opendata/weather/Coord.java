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
@JsonPropertyOrder({"lon", "lat"})

public class Coord {

    @JsonProperty("lon")
    private Double lon;

    @JsonProperty("lat")
    private Double lat;

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * Constructor with all known fields.
     *
     * @param lon the longitude value
     * @param lat the latitude value
     */
    public Coord(Double lon, Double lat) {
        super();
        this.lon = lon;
        this.lat = lat;
    }

    /**
     * Gets the longitude value of this coordinate.
     *
     * @return the longitude
     */
    @JsonProperty("lon")
    public Double getLon() {
        return lon;
    }

    /**
     * Sets the longitude value of this coordinate.
     *
     * @param lon the longitude to set
     */
    @JsonProperty("lon")
    public void setLon(Double lon) {
        this.lon = lon;
    }

    /**
     * Gets the latitude value of this coordinate.
     *
     * @return the latitude
     */
    @JsonProperty("lat")
    public Double getLat() {
        return lat;
    }

    /**
     * Sets the latitude value of this coordinate.
     *
     * @param lat the latitude to set
     */
    @JsonProperty("lat")
    public void setLat(Double lat) {
        this.lat = lat;
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
