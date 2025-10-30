package com.bellias.opendata.wikipedia;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"pages"})

public class Query {

    @JsonProperty("pages")
    private List<Page> pages = null;

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * No-args constructor for serialization/deserialization purposes.
     */
    public Query() {}

    /**
     * Constructor with all fields.
     *
     * @param pages list of pages returned by the API
     */
    public Query(List<Page> pages) {
        super();
        this.pages = pages;
    }

    /**
     * Returns the list of pages from the query.
     *
     * @return list of {@link Page} objects
     */
    @JsonProperty("pages")
    public List<Page> getPages() {
        return pages;
    }

    /**
     * Sets the list of pages from the query.
     *
     * @param pages list of {@link Page} objects
     */
    @JsonProperty("pages")
    public void setPages(List<Page> pages) {
        this.pages = pages;
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
     * Returns a string representation of the object including pages and additional properties.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("pages", pages)
                .append("additionalProperties", additionalProperties)
                .toString();
    }
}
