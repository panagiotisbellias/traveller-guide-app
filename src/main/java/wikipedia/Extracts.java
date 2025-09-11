
package wikipedia;

import java.util.*;
import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "warnings"
})

/** The Construction of a class that is used for retrieving MediaWiki API.
* @since 31-05-2020
* @version 1.4
* @author John Violos */
public class Extracts {

    @JsonProperty("warnings")
    private String warnings;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Extracts() {
    }

    /**
     * 
     * @param warnings
     */
    public Extracts(String warnings) {
        super();
        this.warnings = warnings;
    }

    @JsonProperty("warnings")
    public String getWarnings() {
        return warnings;
    }

    @JsonProperty("warnings")
    public void setWarnings(String warnings) {
        this.warnings = warnings;
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
        return new ToStringBuilder(this).append("warnings", warnings).append("additionalProperties", additionalProperties).toString();
    }

}
