package com.bellias.travellerguide;

import com.bellias.rest.OpenDataRest;

import java.util.ArrayList;
import java.util.Date;

/**
 * The Construction of a class that represents a tourist traveller. Extends from class Traveller.
 *
 * @author Panagiotis Bellias
 */
public class Tourist extends Traveller {

    /**
     * The constructor defines the fields which describe the traveller calling Traveller constructor.
     *
     * @param name the name of the traveller.
     * @param birthDate the birthdate of the traveller.
     * @param currentLat the current latitude of the traveller.
     * @param currentLon the current longitude of the traveller.
     * @param travellerData the criteria traveller wants to his finally suggested city.
     * @param suggestedCities the cities that traveller wants.
     * @param customerID the id of the traveller.
     */
    public Tourist(
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
     * The method calculates similarity between city's data and traveller's preferences, considering
     * the frequency of criteria to simulate their occurrence within a city.
     *
     * @param destinationCity the city object we want to find its similarity to the traveller.
     * @return a double number that represents percentage of similarity between city and traveller.
     */
    @Override
    public double Similarity(City destinationCity) {

        double similarityValue;

        ArrayList<String> travelerList = listOfDistinctWords(getTravellerData());
        String cityList = destinationCity.getCityData();
        int similarityCounter = 0;
        int totalFrequency = 0;
        int frequency;
        ArrayList<Integer> frequencyTable = new ArrayList<>();

        for (String s : travelerList) {
            if (cityList.contains(s)) {
                similarityCounter++;
                frequency = OpenDataRest.countCriterionOfCity(cityList, s);
                frequencyTable.add(frequency);
                totalFrequency += frequency;
            }
        }

        similarityValue = similarityCounter / (double) travelerList.size();

        for (Integer integer : frequencyTable) {
            try {
                similarityValue *= integer / (double) totalFrequency;
            } catch (ArithmeticException e) {
                similarityValue = 0.0;
                break;
            }
        }

        return similarityValue;
    }

}
