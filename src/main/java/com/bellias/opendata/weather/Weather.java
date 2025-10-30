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
@JsonPropertyOrder({"id", "main", "description", "icon"})

public class Weather {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("main")
    private String main;

    @JsonProperty("description")
    private String description;

    @JsonProperty("icon")
    private String icon;

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * No-args constructor for serialization/deserialization purposes.
     */
    public Weather() {}

    /**
     * Constructor with all known fields.
     *
     * @param id internal API ID for weather
     * @param main main weather type (e.g., "Clear", "Rain")
     * @param description detailed description of weather (e.g., "light rain")
     * @param icon weather icon code corresponding to the condition
     */
    public Weather(Integer id, String main, String description, String icon) {
        super();
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    /** Gets the internal API ID for this weather. */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /** Sets the internal API ID for this weather. */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /** Gets the main weather type (e.g., "Clear", "Rain"). */
    @JsonProperty("main")
    public String getMain() {
        return main;
    }

    /** Sets the main weather type (e.g., "Clear", "Rain"). */
    @JsonProperty("main")
    public void setMain(String main) {
        this.main = main;
    }

    /** Gets the detailed description of the weather (e.g., "light rain"). */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /** Sets the detailed description of the weather (e.g., "light rain"). */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /** Gets the weather icon code corresponding to this condition. */
    @JsonProperty("icon")
    public String getIcon() {
        return icon;
    }

    /** Sets the weather icon code corresponding to this condition. */
    @JsonProperty("icon")
    public void setIcon(String icon) {
        this.icon = icon;
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
