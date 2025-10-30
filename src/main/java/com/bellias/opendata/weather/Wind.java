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
@JsonPropertyOrder({"speed", "deg"})

public class Wind {

    @JsonProperty("speed")
    private Double speed;

    @JsonProperty("deg")
    private Integer deg;

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * No-args constructor for serialization/deserialization purposes.
     */
    public Wind() {}

    /**
     * Constructor with all known fields.
     *
     * @param speed wind speed in meters/second
     * @param deg wind direction in degrees (meteorological)
     */
    public Wind(Double speed, Integer deg) {
        super();
        this.speed = speed;
        this.deg = deg;
    }

    /** Gets the wind speed in meters/second. */
    @JsonProperty("speed")
    public Double getSpeed() {
        return speed;
    }

    /** Sets the wind speed in meters/second. */
    @JsonProperty("speed")
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    /** Gets the wind direction in degrees (meteorological). */
    @JsonProperty("deg")
    public Integer getDeg() {
        return deg;
    }

    /** Sets the wind direction in degrees (meteorological). */
    @JsonProperty("deg")
    public void setDeg(Integer deg) {
        this.deg = deg;
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
