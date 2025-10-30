package com.bellias.travellerguide;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the collaborative filtering recommendation logic.
 * Suggests cities based on similarity between travellers' preferences.
 *
 * @author Panagiotis Bellias
 */
public class CollaborativeFiltering {

    /**
     * Generates recommended cities for a traveller using collaborative filtering.
     *
     * @param collectionTravellers all travellers in the system.
     * @param candidateTraveller   the traveller to recommend cities for.
     * @return a list of recommended cities sorted by rank (highest first).
     */
    public static List<RecommendedCity> getRecommendations(
            ArrayList<Traveller> collectionTravellers, Traveller candidateTraveller) {

        if (candidateTraveller.getVisit().isEmpty()) {
            return new ArrayList<>(); // No history → no recommendations
        }

        ArrayList<String> candidateTravellerCriteria = candidateTraveller.getTravellerData();
        ArrayList<String> candidateVisited = candidateTraveller.getVisit();

        // Build a ranked list of city recommendations based on traveller similarity
        return collectionTravellers.stream()
                .filter(t -> !t.equals(candidateTraveller)) // Exclude the candidate traveller
                .flatMap(t ->
                        t.getVisit().stream()
                                .filter(city -> !candidateVisited.contains(city)) // Exclude already visited cities
                                .map(city ->
                                        new RecommendedCity(
                                                city,
                                                innerDot(t.getTravellerData(), candidateTravellerCriteria))))
                .filter(rc -> rc.getRank() > 0)
                .sorted(Comparator.comparingDouble(RecommendedCity::getRank).reversed()) // Highest rank first
                .collect(Collectors.toList());
    }

    /**
     * Calculates the similarity score between two travellers' criteria.
     *
     * @param currentTravellerCriteria   the first traveller’s criteria.
     * @param candidateTravellerCriteria the candidate traveller’s criteria.
     * @return the number of matching criteria (similarity score).
     */
    private static int innerDot(
            ArrayList<String> currentTravellerCriteria, ArrayList<String> candidateTravellerCriteria) {

        int sum = 0;
        for (String criteria : candidateTravellerCriteria) {
            if (currentTravellerCriteria.contains(criteria)) {
                sum++;
            }
        }
        return sum;
    }

}
