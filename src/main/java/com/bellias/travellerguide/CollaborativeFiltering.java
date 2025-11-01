package com.bellias.travellerguide;

import com.bellias.exception.RecommendationException;

import java.util.ArrayList;
import java.util.Collections;
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

    public List<RecommendedCity> getRecommendations(List<Traveller> travellers, Traveller candidateTraveller) throws RecommendationException {

        // No history → no recommendations
        if (candidateTraveller.getVisit().isEmpty()) {
            return Collections.emptyList();
        }

        List<String> candidateTravellerCriteria = candidateTraveller.getTravellerData();
        List<String> candidateVisited = candidateTraveller.getVisit();

        // Build ranked recommendations based on similarity
        return travellers.stream()
                .filter(t -> !t.equals(candidateTraveller)) // Exclude self
                .flatMap(t ->
                        t.getVisit().stream()
                                .filter(city -> !candidateVisited.contains(city)) // Exclude already visited
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
            ArrayList<String> currentTravellerCriteria, List<String> candidateTravellerCriteria) {

        int sum = 0;
        for (String criteria : candidateTravellerCriteria) {
            if (currentTravellerCriteria.contains(criteria)) {
                sum++;
            }
        }
        return sum;
    }

}
