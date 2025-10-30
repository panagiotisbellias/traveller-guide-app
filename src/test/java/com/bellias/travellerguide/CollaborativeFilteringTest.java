package com.bellias.travellerguide;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CollaborativeFilteringTest {

  @Test
  void shouldGenerateRecommendationsForTraveller() {
    ArrayList<String> travellerData = new ArrayList<>(List.of("Culture", "Museums"));
    ArrayList<String> suggestedCities = new ArrayList<>(List.of("Paris", "Rome"));
    Traveller candidate =
        new Traveller("John", new Date(), 37.9838, 23.7275, travellerData, suggestedCities, 1001);

    // Another traveller in the system
    ArrayList<String> otherTravellerData = new ArrayList<>(List.of("Culture", "Art"));
    ArrayList<String> otherSuggestedCities = new ArrayList<>(List.of("Florence", "Venice"));
    Traveller otherTraveller =
        new Traveller(
            "Alice",
            new Date(),
            41.9028,
            12.4964, // Rome lat/lon
            otherTravellerData,
            otherSuggestedCities,
            1002);

    ArrayList<Traveller> allTravellers = new ArrayList<>();
    allTravellers.add(otherTraveller); // add other traveller
    allTravellers.add(candidate); // candidate too (optional)

    List<RecommendedCity> recommendations = CollaborativeFiltering.getRecommendations(allTravellers, candidate);

    assertNotNull(recommendations, "Recommendations list should not be null");
    assertFalse(recommendations.isEmpty(), "Recommendations should not be empty");
  }

  @Test
  void shouldReturnEmptyWhenTravellerHasNoHistory() {
    ArrayList<String> travellerData = new ArrayList<>(List.of("Nature"));
    ArrayList<String> suggestedCities = new ArrayList<>(); // no history
    Traveller traveller =
        new Traveller(
            "Alice",
            new Date(),
            40.6401, // Thessaloniki lat
            22.9444, // lon
            travellerData,
            suggestedCities,
            1002);

    ArrayList<Traveller> allTravellers = new ArrayList<>();
    allTravellers.add(traveller);
    List<RecommendedCity> recommendations = CollaborativeFiltering.getRecommendations(allTravellers, traveller);

    assertTrue(
        recommendations.isEmpty(), "Traveller with no history should yield no recommendations");
  }
}
