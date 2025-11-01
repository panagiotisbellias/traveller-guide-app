package com.bellias.travellerguide.service;

import com.bellias.exception.RecommendationException;
import com.bellias.travellerguide.RecommendedCity;
import com.bellias.travellerguide.Traveller;

import java.util.List;

public interface RecommendationService {

    List<RecommendedCity> recommend(List<Traveller> travellers, Traveller candidateTraveller) throws RecommendationException;

}
