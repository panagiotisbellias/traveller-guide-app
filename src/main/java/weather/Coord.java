
package weather;

import java.util.*;
import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "lon",
    "lat"
})

/** The Construction of a class that represents coordinators.
* @since 31-05-2020
* @version 1.4
* @author John Violos */
public class Coord {

    @JsonProperty("lon")
    private Double lon;
    @JsonProperty("lat")
    private Double lat;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Coord() {
    }

    /**
     * 
     * @param lon
     * @param lat
     */
    public Coord(Double lon, Double lat) {
        super();
        this.lon = lon;
        this.lat = lat;
    }

    @JsonProperty("lon")
    public Double getLon() {
        return lon;
    }

    @JsonProperty("lon")
    public void setLon(Double lon) {
        this.lon = lon;
    }

    @JsonProperty("lat")
    public Double getLat() {
        return lat;
    }

    @JsonProperty("lat")
    public void setLat(Double lat) {
        this.lat = lat;
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
