package com.bellias.opendata.wikipedia;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"warnings"})

public class Extracts {

    @JsonProperty("warnings")
    private String warnings;

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * Constructor with warnings field.
     *
     * @param warnings the warning message returned by the MediaWiki API
     */
    public Extracts(String warnings) {
        super();
        this.warnings = warnings;
    }

    /**
     * Returns the warning message returned by the MediaWiki API.
     *
     * @return warnings message
     */
    @JsonProperty("warnings")
    public String getWarnings() {
        return warnings;
    }

    /**
     * Sets the warning message returned by the MediaWiki API.
     *
     * @param warnings warnings message
     */
    @JsonProperty("warnings")
    public void setWarnings(String warnings) {
        this.warnings = warnings;
    }

    /**
     * Returns a map of additional properties that were not explicitly defined in this class.
     *
     * @return map of additional properties
     */
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    /**
     * Adds a property that was not explicitly defined in this class.
     *
     * @param name name of the property
     * @param value value of the property
     */
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    /**
     * Returns a string representation of the object including warnings and additional properties.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("warnings", warnings)
                .append("additionalProperties", additionalProperties)
                .toString();
    }

}
