package com.bellias.travellerguide.service;

import com.bellias.exception.RecommendationException;
import com.bellias.travellerguide.CollaborativeFiltering;
import com.bellias.travellerguide.RecommendedCity;
import com.bellias.travellerguide.Traveller;

import java.util.List;

public record CollaborativeFilteringRecommendationService(CollaborativeFiltering engine) implements RecommendationService {

    @Override
    public List<RecommendedCity> recommend(List<Traveller> travellers, Traveller candidateTraveller) throws RecommendationException {
        return engine.getRecommendations(travellers, candidateTraveller);
    }

}
