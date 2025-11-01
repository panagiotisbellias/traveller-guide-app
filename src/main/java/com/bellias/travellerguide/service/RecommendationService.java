package com.bellias.travellerguide.service;

import com.bellias.exception.RecommendationException;
import com.bellias.travellerguide.City;
import com.bellias.travellerguide.RecommendedCity;
import com.bellias.travellerguide.Traveller;

import java.util.List;

public interface RecommendationService {

    List<RecommendedCity> recommend(
            String appId,
            List<Traveller> travellers,
            List<String> cities,
            List<City> cityObjects,
            int id,
            boolean manyTravellers
    ) throws RecommendationException;

}
