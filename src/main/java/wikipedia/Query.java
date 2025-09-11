
package wikipedia;

import java.util.*;
import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pages"
})

/** The Construction of a class that is used for retrieving MediaWiki API.
* @since 31-05-2020
* @version 1.4
* @author John Violos */
public class Query {

    @JsonProperty("pages")
    private List<Page> pages = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Query() {
    }

    /**
     * 
     * @param pages
     */
    public Query(List<Page> pages) {
        super();
        this.pages = pages;
    }

    @JsonProperty("pages")
    public List<Page> getPages() {
        return pages;
    }

    @JsonProperty("pages")
    public void setPages(List<Page> pages) {
        this.pages = pages;
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
        return new ToStringBuilder(this).append("pages", pages).append("additionalProperties", additionalProperties).toString();
    }

}
