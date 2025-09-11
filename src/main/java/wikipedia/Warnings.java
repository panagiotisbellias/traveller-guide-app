
package wikipedia;

import java.util.*;
import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "extracts"
})

/** The Construction of a class that is used for retrieving MediaWiki API.
* @since 31-05-2020
* @version 1.4
* @author John Violos */
public class Warnings {

    @JsonProperty("extracts")
    private Extracts extracts;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Warnings() {
    }

    /**
     * 
     * @param extracts
     */
    public Warnings(Extracts extracts) {
        super();
        this.extracts = extracts;
    }

    @JsonProperty("extracts")
    public Extracts getExtracts() {
        return extracts;
    }

    @JsonProperty("extracts")
    public void setExtracts(Extracts extracts) {
        this.extracts = extracts;
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
        return new ToStringBuilder(this).append("extracts", extracts).append("additionalProperties", additionalProperties).toString();
    }

}
