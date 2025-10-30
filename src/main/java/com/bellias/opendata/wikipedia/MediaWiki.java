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
@JsonPropertyOrder({"batchcomplete", "warnings", "query"})

public class MediaWiki {

    @JsonProperty("batchcomplete")
    private Boolean batchcomplete;

    @JsonProperty("warnings")
    private Warnings warnings;

    @JsonProperty("query")
    private Query query;

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * No-args constructor for serialization/deserialization purposes.
     */
    public MediaWiki() {}

    /**
     * Constructor with all fields.
     *
     * @param batchcomplete indicates whether the batch operation is complete
     * @param warnings warnings returned by the API
     * @param query query result containing pages or extracts
     */
    public MediaWiki(Boolean batchcomplete, Warnings warnings, Query query) {
        super();
        this.batchcomplete = batchcomplete;
        this.warnings = warnings;
        this.query = query;
    }

    /**
     * Returns whether the batch operation is complete.
     *
     * @return true if batch is complete, false otherwise
     */
    @JsonProperty("batchcomplete")
    public Boolean getBatchcomplete() {
        return batchcomplete;
    }

    /**
     * Sets the batch completion status.
     *
     * @param batchcomplete true if batch is complete, false otherwise
     */
    @JsonProperty("batchcomplete")
    public void setBatchcomplete(Boolean batchcomplete) {
        this.batchcomplete = batchcomplete;
    }

    /**
     * Returns warnings returned by the MediaWiki API.
     *
     * @return warnings object
     */
    @JsonProperty("warnings")
    public Warnings getWarnings() {
        return warnings;
    }

    /**
     * Sets warnings returned by the MediaWiki API.
     *
     * @param warnings warnings object
     */
    @JsonProperty("warnings")
    public void setWarnings(Warnings warnings) {
        this.warnings = warnings;
    }

    /**
     * Returns the query result containing pages or extracts.
     *
     * @return query object
     */
    @JsonProperty("query")
    public Query getQuery() {
        return query;
    }

    /**
     * Sets the query result containing pages or extracts.
     *
     * @param query query object
     */
    @JsonProperty("query")
    public void setQuery(Query query) {
        this.query = query;
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
     * Returns a string representation of the object including batch status, warnings, query,
     * and additional properties.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("batchcomplete", batchcomplete)
                .append("warnings", warnings)
                .append("query", query)
                .append("additionalProperties", additionalProperties)
                .toString();
    }

}
