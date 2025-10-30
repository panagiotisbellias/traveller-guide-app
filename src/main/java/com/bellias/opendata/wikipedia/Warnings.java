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
@JsonPropertyOrder({"extracts"})

public class Warnings {

    @JsonProperty("extracts")
    private Extracts extracts;

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * No-args constructor for serialization/deserialization purposes.
     */
    public Warnings() {}

    /**
     * Constructor with all fields.
     *
     * @param extracts the {@link Extracts} object containing warning messages
     */
    public Warnings(Extracts extracts) {
        super();
        this.extracts = extracts;
    }

    /**
     * Returns the extracts object containing warning messages.
     *
     * @return {@link Extracts} object
     */
    @JsonProperty("extracts")
    public Extracts getExtracts() {
        return extracts;
    }

    /**
     * Sets the extracts object containing warning messages.
     *
     * @param extracts {@link Extracts} object
     */
    @JsonProperty("extracts")
    public void setExtracts(Extracts extracts) {
        this.extracts = extracts;
    }

    /**
     * Returns a map of additional properties not explicitly defined in this class.
     *
     * @return map of additional properties
     */
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    /**
     * Adds a property not explicitly defined in this class.
     *
     * @param name name of the property
     * @param value value of the property
     */
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    /**
     * Returns a string representation of the object including extracts and additional properties.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("extracts", extracts)
                .append("additionalProperties", additionalProperties)
                .toString();
    }
}
