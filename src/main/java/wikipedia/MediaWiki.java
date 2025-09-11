
package wikipedia;

import java.util.*;
import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "batchcomplete",
    "warnings",
    "query"
})

/** The Construction of a class that represents MediaWiki API.
* @since 31-05-2020
* @version 1.4
* @author John Violos */
public class MediaWiki {

    @JsonProperty("batchcomplete")
    private Boolean batchcomplete;
    @JsonProperty("warnings")
    private Warnings warnings;
    @JsonProperty("query")
    private Query query;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public MediaWiki() {
    }

    /**
     * 
     * @param batchcomplete
     * @param warnings
     * @param query
     */
    public MediaWiki(Boolean batchcomplete, Warnings warnings, Query query) {
        super();
        this.batchcomplete = batchcomplete;
        this.warnings = warnings;
        this.query = query;
    }

    @JsonProperty("batchcomplete")
    public Boolean getBatchcomplete() {
        return batchcomplete;
    }

    @JsonProperty("batchcomplete")
    public void setBatchcomplete(Boolean batchcomplete) {
        this.batchcomplete = batchcomplete;
    }

    @JsonProperty("warnings")
    public Warnings getWarnings() {
        return warnings;
    }

    @JsonProperty("warnings")
    public void setWarnings(Warnings warnings) {
        this.warnings = warnings;
    }

    @JsonProperty("query")
    public Query getQuery() {
        return query;
    }

    @JsonProperty("query")
    public void setQuery(Query query) {
        this.query = query;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("batchcomplete", batchcomplete).append("warnings", warnings).append("query", query).append("additionalProperties", additionalProperties).toString();
    }

}
