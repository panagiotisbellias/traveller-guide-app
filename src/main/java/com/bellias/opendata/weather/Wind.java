package com.bellias.opendata.weather;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"speed", "deg"})

/**
 * The Construction of a class that is used for retrieving OpenWeatherMap API.
 *
 * @author Panagiotis Bellias, John Violos
 */
public class Wind {

  @JsonProperty("speed")
  private Double speed;

  @JsonProperty("deg")
  private Integer deg;

  @JsonIgnore private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  /** No args constructor for use in serialization */
  public Wind() {}

  /**
   * @param deg
   * @param speed
   */
  public Wind(Double speed, Integer deg) {
    super();
    this.speed = speed;
    this.deg = deg;
  }

  @JsonProperty("speed")
  public Double getSpeed() {
    return speed;
  }

  @JsonProperty("speed")
  public void setSpeed(Double speed) {
    this.speed = speed;
  }

  @JsonProperty("deg")
  public Integer getDeg() {
    return deg;
  }

  @JsonProperty("deg")
  public void setDeg(Integer deg) {
    this.deg = deg;
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
