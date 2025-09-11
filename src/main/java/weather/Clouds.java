
package weather;

import java.util.*;
import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "all"
})

/** The Construction of a class that represents clouds.
* @since 31-05-2020
* @version 1.4
* @author John Violos */
public class Clouds {

    @JsonProperty("all")
    private Integer all;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Clouds() {
    }

    /**
     * 
     * @param all
     */
    public Clouds(Integer all) {
        super();
        this.all = all;
    }

    @JsonProperty("all")
    public Integer getAll() {
        return all;
    }

    @JsonProperty("all")
    public void setAll(Integer all) {
        this.all = all;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
