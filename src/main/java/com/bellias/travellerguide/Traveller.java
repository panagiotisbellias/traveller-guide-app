package com.bellias.travellerguide;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * Represents a traveller with personal information, preferences, visited cities, and suggested
 * cities. Implements Comparable to allow sorting by age and Serializable for persistence.
 *
 * <p>Provides methods to calculate similarity with cities, find the most suitable traveller for
 * promotions, and manage traveller data.</p>
 *
 * @author Panagiotis Bellias
 */
public class Traveller implements Comparable<Traveller>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private Date birthDate;
    private int age;
    private int customerID;
    private double currentLat;
    private double currentLon;
    private ArrayList<String> suggestedCities;
    private ArrayList<String> travellerData;
    private static int travellersNumber;
    private final String visit;
    private ArrayList<String> visitedCities;

    /**
     * Compares two Traveller objects by age.
     *
     * @param tr the Traveller object to compare with
     * @return positive if this traveller is older, negative if younger, 0 if same age
     */
    @Override
    public int compareTo(Traveller tr) {
        return this.age - tr.age;
    }

    /**
     * Constructs a Traveller with all fields initialized.
     *
     * @param name the traveller's name
     * @param birthDate the traveller's birthdate
     * @param currentLat current latitude
     * @param currentLon current longitude
     * @param suggestedCities cities the traveller is interested in
     * @param travellerData traveller's criteria/preferences
     * @param visitedCities cities already visited
     * @param visit city recommended to traveller
     * @param customerID traveller ID
     */
    public Traveller(
            String name,
            Date birthDate,
            double currentLat,
            double currentLon,
            ArrayList<String> suggestedCities,
            ArrayList<String> travellerData,
            ArrayList<String> visitedCities,
            String visit,
            int customerID) {

        this.name = name;
        this.birthDate = birthDate;
        age = findAge(birthDate);
        this.currentLat = currentLat;
        this.currentLon = currentLon;
        this.suggestedCities = suggestedCities;
        this.travellerData = travellerData;
        this.visit = visit;
        this.visitedCities = (visitedCities != null) ? visitedCities : new ArrayList<>();
        this.customerID = customerID;
    }

    /**
     * Constructs a Traveller without a recommended city ("visit").
     *
     * @param name the traveller's name
     * @param birthDate the traveller's birthdate
     * @param currentLat current latitude
     * @param currentLon current longitude
     * @param travellerData traveller's criteria/preferences
     * @param suggestedCities cities the traveller is interested in
     * @param customerID traveller ID
     */
    public Traveller(
            String name,
            Date birthDate,
            double currentLat,
            double currentLon,
            ArrayList<String> travellerData,
            ArrayList<String> suggestedCities,
            int customerID) {

        this.name = name;
        this.birthDate = birthDate;
        age = findAge(birthDate);
        this.currentLat = currentLat;
        this.currentLon = currentLon;
        this.travellerData = travellerData;
        this.suggestedCities = suggestedCities;
        this.customerID = customerID;
        this.visit = "";
        this.visitedCities = new ArrayList<>(this.suggestedCities);
    }

    /** Constructs a Traveller with default empty values. */
    public Traveller() {
        name = "";
        birthDate = new Date();
        age = 0;
        currentLat = 0.0;
        currentLon = 0.0;
        travellerData = new ArrayList<>();
        suggestedCities = new ArrayList<>();
        customerID = 0;
        visit = "";
        visitedCities = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public double getCurrentLat() {
        return currentLat;
    }

    public void setCurrentLat(double currentLat) {
        this.currentLat = currentLat;
    }

    public double getCurrentLon() {
        return currentLon;
    }

    public void setCurrentLon(double currentLon) {
        this.currentLon = currentLon;
    }

    public ArrayList<String> getSuggestedCities() {
        return suggestedCities;
    }

    public void setSuggestedCities(ArrayList<String> suggestedCities) {
        this.suggestedCities = suggestedCities;
    }

    public ArrayList<String> getTravellerData() {
        return travellerData;
    }

    public void setTravellerData(ArrayList<String> travellerData) {
        this.travellerData = travellerData;
    }

    public static int getTravellersNumber() {
        return travellersNumber;
    }

    public static void setTravellersNumber(int travellersNumber) {
        Traveller.travellersNumber = travellersNumber;
    }

    public ArrayList<String> getVisit() {
        return visitedCities;
    }

    public void setVisit(ArrayList<String> visitedCities) {
        this.visitedCities = (visitedCities != null) ? visitedCities : new ArrayList<>();
    }

    /**
     * Calculates the similarity between a traveller's preferences and a city's data.
     *
     * @param city the city to compare with
     * @return similarity percentage (0.0–1.0)
     */
    public double Similarity(City city) {
        double similarityValue;
        ArrayList<String> travellerList = new ArrayList<>(listOfDistinctWords(travellerData));
        String cityData = city.getCityData();
        int similarityCounter = 0;

        for (String criterion : travellerList) {
            if (cityData.contains(criterion)) {
                similarityCounter++;
            }
        }

        try {
            similarityValue = (double) similarityCounter / travellerList.size();
        } catch (ArithmeticException e) {
            similarityValue = 0.0;
        }

        return similarityValue;
    }

    /**
     * Removes duplicates from a list of strings.
     *
     * @param str list of strings
     * @return new list without duplicates
     */
    public static ArrayList<String> listOfDistinctWords(ArrayList<String> str) {
        ArrayList<String> list = new ArrayList<>();
        for (String s : str) {
            if (!list.contains(s)) {
                list.add(s);
            }
        }
        return list;
    }

    /**
     * Compares multiple cities and returns the city with the highest similarity to the traveller.
     *
     * @param Cities list of cities to compare
     * @return city with the highest similarity
     */
    public City CompareCities(ArrayList<City> Cities) {
        City withMostSimilarityCity = null;

        if (Cities.size() >= 2) {
            for (int i = 0; i < Cities.size() - 1; i++) {
                if (Similarity(Cities.get(i)) <= Similarity(Cities.get(i + 1))) {
                    withMostSimilarityCity = Cities.get(i + 1);
                } else {
                    withMostSimilarityCity = Cities.get(i);
                }
            }
        } else if (!Cities.isEmpty()) {
            withMostSimilarityCity = Cities.getFirst();
        }

        return withMostSimilarityCity;
    }

    /**
     * Filters cities by excluding undesired weather and returns the most similar city.
     *
     * @param weather undesired weather
     * @param cities list of cities to compare
     * @return city with maximum similarity
     */
    public City CompareCities(String weather, ArrayList<City> cities) {
        ArrayList<City> NotExcludedCities = new ArrayList<>();
        for (City city : cities) {
            if (!city.getWeather().equals(weather)) {
                NotExcludedCities.add(city);
            }
        }
        return CompareCities(NotExcludedCities);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Traveller traveller)) return false;
        return getName().equals(traveller.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, customerID, currentLat, currentLon, travellerData,
                suggestedCities, visit);
    }

    /**
     * Finds the most qualified traveller for a city promotion based on similarity.
     *
     * @param travellers list of travellers to consider
     * @param city city for the promotion
     * @return qualified traveller
     */
    public static Traveller findQualifiedCustomer(ArrayList<Traveller> travellers, City city) {
        if (travellers.isEmpty()) return null;
        Traveller qualifiedCustomer = travellers.getFirst();

        for (int i = 1; i < travellers.size(); i++) {
            Traveller prev = travellers.get(i - 1);
            Traveller curr = travellers.get(i);
            double prevSim = prev.Similarity(city);
            double currSim = curr.Similarity(city);

            if (prevSim == currSim) {
                qualifiedCustomer = (prev.getCustomerID() < curr.getCustomerID()) ? prev : curr;
            } else {
                qualifiedCustomer = (prevSim < currSim) ? curr : prev;
            }
        }

        return qualifiedCustomer;
    }

    /**
     * Sets the ages for all travellers based on their birthdays.
     *
     * @param travellers list of travellers
     */
    public static void setAgesForAll(ArrayList<Traveller> travellers) {
        for (Traveller tr : travellers) {
            tr.setAge(findAge(tr.getBirthDate()));
        }
    }

    /**
     * Calculates the age of a traveller from their birthdate.
     *
     * @param birthDate the birthdate
     * @return calculated age in years
     */
    private static int findAge(Date birthDate) {
        Calendar birthDay = Calendar.getInstance();
        birthDay.setTimeInMillis(birthDate.getTime());

        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(System.currentTimeMillis());

        int years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
        int currMonth = now.get(Calendar.MONTH) + 1;
        int birthMonth = birthDay.get(Calendar.MONTH) + 1;
        int months = currMonth - birthMonth;

        if (months < 0) {
            years--;
            months = 12 - birthMonth + currMonth;
            if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) months--;
        } else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
            years--;
            months = 11;
        }

        if (now.get(Calendar.DATE) <= birthDay.get(Calendar.DATE)) {
            if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
                now.add(Calendar.MONTH, -1);
            } else {
                if (months == 12) years++;
            }
        }

        return years;
    }
}
