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
@JsonPropertyOrder({"pageid", "ns", "title", "extract"})

public class Page {

    @JsonProperty("pageid")
    private Integer pageid;

    @JsonProperty("ns")
    private Integer ns;

    @JsonProperty("title")
    private String title;

    @JsonProperty("extract")
    private String extract;

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<>();

    /**
     * No-args constructor for serialization/deserialization purposes.
     */
    public Page() {}

    /**
     * Constructor with all fields.
     *
     * @param pageid the unique identifier of the page
     * @param ns the namespace of the page
     * @param title the title of the page
     * @param extract the content extract of the page
     */
    public Page(Integer pageid, Integer ns, String title, String extract) {
        super();
        this.pageid = pageid;
        this.ns = ns;
        this.title = title;
        this.extract = extract;
    }

    /**
     * Returns the unique identifier of the page.
     *
     * @return page ID
     */
    @JsonProperty("pageid")
    public Integer getPageid() {
        return pageid;
    }

    /**
     * Sets the unique identifier of the page.
     *
     * @param pageid page ID
     */
    @JsonProperty("pageid")
    public void setPageid(Integer pageid) {
        this.pageid = pageid;
    }

    /**
     * Returns the namespace of the page.
     *
     * @return namespace
     */
    @JsonProperty("ns")
    public Integer getNs() {
        return ns;
    }

    /**
     * Sets the namespace of the page.
     *
     * @param ns namespace
     */
    @JsonProperty("ns")
    public void setNs(Integer ns) {
        this.ns = ns;
    }

    /**
     * Returns the title of the page.
     *
     * @return title
     */
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the page.
     *
     * @param title page title
     */
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the content extract of the page.
     *
     * @return page extract
     */
    @JsonProperty("extract")
    public String getExtract() {
        return extract;
    }

    /**
     * Sets the content extract of the page.
     *
     * @param extract page extract
     */
    @JsonProperty("extract")
    public void setExtract(String extract) {
        this.extract = extract;
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
     * Returns a string representation of the object including page ID, namespace, title,
     * extract, and additional properties.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("pageid", pageid)
                .append("ns", ns)
                .append("title", title)
                .append("extract", extract)
                .append("additionalProperties", additionalProperties)
                .toString();
    }

}
