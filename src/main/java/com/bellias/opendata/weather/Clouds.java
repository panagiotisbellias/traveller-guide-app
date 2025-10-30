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
@JsonPropertyOrder({"all"})

public class Clouds {

    @JsonProperty("all")
    private Integer all;

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * Constructor with all known fields.
     *
     * @param all the cloudiness percentage (0-100)
     */
    public Clouds(Integer all) {
        super();
        this.all = all;
    }

    /**
     * Gets the cloudiness percentage.
     *
     * @return the cloudiness percentage (0-100)
     */
    @JsonProperty("all")
    public Integer getAll() {
        return all;
    }

    /**
     * Sets the cloudiness percentage.
     *
     * @param all the cloudiness percentage (0-100)
     */
    @JsonProperty("all")
    public void setAll(Integer all) {
        this.all = all;
    }

    /**
     * Returns a map of any additional properties not explicitly defined in this class.
     *
     * @return a map of additional properties
     */
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    /**
     * Adds a property to this object. Used during deserialization for unknown fields.
     *
     * @param name  the property name
     * @param value the property value
     */
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
