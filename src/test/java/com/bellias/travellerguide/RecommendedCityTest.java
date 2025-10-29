package com.bellias.travellerguide;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RecommendedCityTest {

  @Test
  void shouldStoreCityInformationCorrectly() {
    RecommendedCity city = new RecommendedCity("Athens", 4.8);

    assertEquals("Athens", city.getCity());
    assertEquals(4.8, city.getRank(), 0.001);
  }

  @Test
  void shouldBeEqualWhenNameAndScoreMatch() {
    RecommendedCity c1 = new RecommendedCity("Rome", 4.5);
    RecommendedCity c2 = new RecommendedCity("Rome", 4.5);

    assertEquals(c1, c2, "Cities with same name and score should be equal");
  }

  @Test
  void toStringShouldContainName() {
    RecommendedCity city = new RecommendedCity("Paris", 4.9);
    assertTrue(city.toString().contains("Paris"));
  }
}
