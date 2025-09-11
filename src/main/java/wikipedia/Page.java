
package wikipedia;

import java.util.*;
import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pageid",
    "ns",
    "title",
    "extract"
})

/** The Construction of a class that is used for retrieving MediaWiki API.
* @since 31-05-2020
* @version 1.4
* @author John Violos */
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
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Page() {
    }

    /**
     * 
     * @param extract
     * @param ns
     * @param pageid
     * @param title
     */
    public Page(Integer pageid, Integer ns, String title, String extract) {
        super();
        this.pageid = pageid;
        this.ns = ns;
        this.title = title;
        this.extract = extract;
    }

    @JsonProperty("pageid")
    public Integer getPageid() {
        return pageid;
    }

    @JsonProperty("pageid")
    public void setPageid(Integer pageid) {
        this.pageid = pageid;
    }

    @JsonProperty("ns")
    public Integer getNs() {
        return ns;
    }

    @JsonProperty("ns")
    public void setNs(Integer ns) {
        this.ns = ns;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("extract")
    public String getExtract() {
        return extract;
    }

    @JsonProperty("extract")
    public void setExtract(String extract) {
        this.extract = extract;
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
        return new ToStringBuilder(this).append("pageid", pageid).append("ns", ns).append("title", title).append("extract", extract).append("additionalProperties", additionalProperties).toString();
    }

}
