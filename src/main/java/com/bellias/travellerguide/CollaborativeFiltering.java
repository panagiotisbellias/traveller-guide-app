package com.bellias.travellerguide;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The Construction of a class that implements collaborative filtering.
 *
 * @author Panagiotis Bellias
 */
public class CollaborativeFiltering {

  // ===========================================================collaborativeFilteringMethod()=========================================================
  /**
   * The method calculates the suggested city according to collaborative filtering.
   *
   * @param collectionTravellers all the travellers existing in the system.
   * @param candidateTraveller   the traveller we want to suggest to him.
   * @return a city object which is the finally suggested city.
   */
  // ==================================================================================================================================================
  public static List<RecommendedCity> getRecommendations(
      ArrayList<Traveller> collectionTravellers, Traveller candidateTraveller) {

      if (candidateTraveller.getVisit().isEmpty()) {
          return new ArrayList<>(); // no history → no recommendations
      }

    ArrayList<String> candidateTravellerCriteria = candidateTraveller.getTravellerData();
      ArrayList<String> candidateVisited = candidateTraveller.getVisit();

      // Map each traveller to RecommendedCity (city + rank)
      List<RecommendedCity> recommendations =
        collectionTravellers.stream()
            .filter(t -> !t.equals(candidateTraveller)) // skip candidate
                .flatMap(t -> t.getVisit().stream()
                        .filter(city -> !candidateVisited.contains(city)) // exclude already visited
                        .map(city -> new RecommendedCity(city,
                                innerDot(t.getTravellerData(), candidateTravellerCriteria))))
            .filter(rc -> rc.getRank() > 0)
            .sorted(Comparator.comparingDouble(RecommendedCity::getRank).reversed()) // highest rank first
            .collect(Collectors.toList());

      return recommendations;
  }

  // =======================================================End of
  // collaborativeFilteringMethod()======================================================

  // ==================================================================innerDot()======================================================================
  /**
   * The method calculates the rank for every traveller for a specific city.
   *
   * @param currentTravellerCriterias first traveller's criteria to compare.
   * @param candidateTravellersCriteria current traveller's criteria to compare.
   * @return the city rank.
   */
  // ==================================================================================================================================================
  private static int innerDot(
      ArrayList<String> currentTravellerCriterias, ArrayList<String> candidateTravellersCriteria) {

    int sum = 0;

    for (String criteria : candidateTravellersCriteria) {
      if (currentTravellerCriterias.contains(criteria)) sum++;
    }

    return sum;
  }
  // =================================================================End of
  // innerDot()================================================================

} // ===========================================================End of Class CollaborativeFiltering
  // =========================================================
