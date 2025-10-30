package com.bellias.travellerguide;

import java.util.ArrayList;
import java.util.Date;

/**
 * Represents a business traveller, extending the base {@link Traveller} class.
 *
 * <p>Provides functionality to calculate similarity between the traveller’s current location and
 * destination cities.
 * <p>
 * author Panagiotis Bellias
 */
public class Business extends Traveller {

    /**
     * Initializes a new Business traveller with the given parameters.
     *
     * @param name the traveller’s name
     * @param birthDate the traveller’s birthdate
     * @param currentLat the current latitude of the traveller
     * @param currentLon the current longitude of the traveller
     * @param travellerData the traveller’s preference criteria
     * @param suggestedCities the cities the traveller is interested in
     * @param customerID the traveller’s unique ID
     */
    public Business(
            String name,
            Date birthDate,
            double currentLat,
            double currentLon,
            ArrayList<String> travellerData,
            ArrayList<String> suggestedCities,
            int customerID) {
        super(name, birthDate, currentLat, currentLon, travellerData, suggestedCities, customerID);
    }

    /**
     * Calculates the similarity between the traveller and a destination city, based on distance.
     *
     * @param destinationCity the city to compare with
     * @return a value representing the distance ratio (similarity) between the traveller and the city
     */
    @Override
    public double Similarity(City destinationCity) {
        final int EARTH_RADIUS = 6371;
        double dist =
                distanceCalculation(
                        getCurrentLat(),
                        getCurrentLon(),
                        destinationCity.getLat(),
                        destinationCity.getLon(),
                        "K");
        return dist / EARTH_RADIUS;
    }

    /**
     * Calculates the distance between two geographic points using latitude and longitude.
     *
     * @param lat1 the traveller’s latitude
     * @param lon1 the traveller’s longitude
     * @param lat2 the city’s latitude
     * @param lon2 the city’s longitude
     * @param unit the unit of measurement ("K" for kilometers, "N" for nautical miles)
     * @return the calculated distance between the two coordinates
     */
    public static double distanceCalculation(
            double lat1, double lon1, double lat2, double lon2, String unit) {

        if (lat1 == lat2 && lon1 == lon2) {
            return 0.0;
        }

        double theta = lon1 - lon2;
        double dist =
                Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
                        + Math.cos(Math.toRadians(lat1))
                        * Math.cos(Math.toRadians(lat2))
                        * Math.cos(Math.toRadians(theta));

        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;

        if (unit.equals("K")) {
            dist *= 1.609344;
        } else if (unit.equals("N")) {
            dist *= 0.8684;
        }

        return dist;
    }
}
