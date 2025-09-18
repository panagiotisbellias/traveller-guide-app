package com.bellias.travellerguide;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;

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
     * @param candidateTraveller the traveller we want to suggest to him.
     * @return a city object which is the finally suggested city.
     */
    // ==================================================================================================================================================
    public static City collaborativeFilteringMethod(
        ArrayList<Traveller> collectionTravellers, Traveller candidateTraveller) {

      ArrayList<String> candidateTravellerCriteria = candidateTraveller.getTravellerData();

      Optional<RecommendedCity> recommendedCity =
          collectionTravellers.stream()
              .map(
                  i ->
                      new RecommendedCity(
                          i.getVisit(), innerDot(i.getTravellerData(), candidateTravellerCriteria)))
              .max(Comparator.comparingInt(RecommendedCity::getRank));

      String city = recommendedCity.get().getCity(); // Set the suggestedCityLabel with this!
      String cityName[] = city.split(", ");

      City suggestedCity = new City(cityName[0], cityName[1]);

      return suggestedCity;
    }

    // =======================================================End of collaborativeFilteringMethod()======================================================

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

      Iterator<String> criterias = candidateTravellersCriteria.iterator();
      while (criterias.hasNext()) {
        String criteria = criterias.next();
        if (currentTravellerCriterias.contains(criteria)) sum++;
      }

      return sum;
    }
    // =================================================================End of innerDot()================================================================

} // ===========================================================End of Class CollaborativeFiltering =========================================================
