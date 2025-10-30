package com.bellias.gui;

import com.bellias.config.AppProperties;
import com.bellias.opendata.weather.OpenWeatherMap;
import com.bellias.rest.WeatherThread;
import com.bellias.storage.DataStoreFactory;
import com.bellias.travellerguide.Business;
import com.bellias.travellerguide.City;
import com.bellias.travellerguide.CollaborativeFiltering;
import com.bellias.travellerguide.RecommendedCity;
import com.bellias.travellerguide.Tourist;
import com.bellias.travellerguide.Traveller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A {@link MouseListener} implementation that drives the application's main data processing
 * logic when triggered by a GUI event.
 * This class collects input from the UI, validates it, retrieves data from OpenWeatherMap,
 * and generates a recommended city for the traveller using either content-based or collaborative
 * filtering.</p>
 * Depending on the traveller type (Traveller, Business, or Tourist), it constructs the
 * appropriate subclass instance and saves the results through the {@link DataStoreFactory}.</p>
 *
 * @author
 *     Panagiotis Bellias
 */
public class DataProcessing implements MouseListener {

    private static final Logger log = LoggerFactory.getLogger(DataProcessing.class);
    private final String APP_ID;
    private final ArrayList<Traveller> travellers;
    private final ArrayList<String> cities;
    private final ArrayList<City> cityObjects;
    private final int id;
    private final boolean manyTravellers;
    private final ArrayList<Boolean> checkFlags = new ArrayList<>();

    /**
     * Constructs a new DataProcessing listener that initializes the necessary context for
     * traveller recommendation processing.
     *
     * @param APP_ID          the OpenWeatherMap API key
     * @param travellers      the list of traveller profiles
     * @param cities          the list of city names in string format
     * @param cityObjects     the list of {@link City} objects
     * @param id              the traveller's unique identifier
     * @param manyTravellers  whether collaborative filtering should be used
     */
    public DataProcessing(
            String APP_ID,
            ArrayList<Traveller> travellers,
            ArrayList<String> cities,
            ArrayList<City> cityObjects,
            int id,
            boolean manyTravellers) {

        this.APP_ID = APP_ID;
        this.travellers = travellers;
        this.cities = cities;
        this.cityObjects = cityObjects;
        this.id = id;
        this.manyTravellers = manyTravellers;
    }

    /**
     * Handles the mouse click event to execute the application's recommendation logic.
     *
     * @param e the mouse event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        int kind = GUI.getKind();
        String name = GUI.getNameText();
        String date = GUI.getDayBox() + "-" + GUI.getMonthBox() + "-" + GUI.getYearBox();

        Date birthDate = new Date();
        checkFlags.add(checkBirthDate(date));

        String baseCity = GUI.getBaseCityText();
        String[] bCity = checkBaseCity(baseCity, checkFlags);

        String guiCities = GUI.getCitiesText();
        checkFlags.add(checkCities(guiCities, cities, cityObjects, APP_ID));

        String criteria = GUI.getCriteriaText();
        ArrayList<String> criteriaSuggestionsOfCustomer = new ArrayList<>();
        checkFlags.add(checkTravellerCriterias(kind, criteria, criteriaSuggestionsOfCustomer));

        GUI.getErrorLabel().setText(GUI.getErrorInputMessage());
        GUI.getErrorLabel().setVisible(true);

        String bName = bCity[0];
        String bCountry = bCity[1];

        // Retrieve data from OpenWeatherMap API
        WeatherThread wt = new WeatherThread("weatherThread", bName, bCountry, APP_ID);
        wt.start();
        while (wt.getOpenWeatherMapObject() == null) {
            System.out.println("Data loading...");
        }

        OpenWeatherMap weatherObject = wt.getOpenWeatherMapObject();
        double lat = 0.0, lon = 0.0;
        try {
            lat = weatherObject.getCoord().getLat();
            lon = weatherObject.getCoord().getLon();
        } catch (NullPointerException ex) {
            log.error("e: ", ex);
        }

        String weather = GUI.getWeatherText();
        City suggestedCity = new City();

        switch (kind) {
            case 1 -> {
                Traveller traveller = new Traveller(
                        name, birthDate, lat, lon, criteriaSuggestionsOfCustomer, cities, id);
                travellers.add(traveller);
                suggestedCity = getSuggestedCity(traveller, weather);
            }
            case 2 -> {
                Business traveller = new Business(name, birthDate, lat, lon, new ArrayList<>(), cities, id);
                travellers.add(traveller);
                suggestedCity = getSuggestedCity(traveller, weather);
            }
            case 3 -> {
                Tourist traveller = new Tourist(
                        name, birthDate, lat, lon, criteriaSuggestionsOfCustomer, cities, id);
                travellers.add(traveller);
                suggestedCity = getSuggestedCity(traveller, weather);
            }
            default -> System.out.println("Unknown traveller type.");
        }

        City.setSuggestedCityToGUI(suggestedCity);
        if (suggestedCity != null) {
            GUI.getSuggestedCity().setVisible(true);
            GUI.getSuggestedCityLabel().setVisible(true);
            GUI.getNewUserLabel().setVisible(true);
        }

        cities.clear();
        cityObjects.clear();

        DataStoreFactory.create().saveTravellers(AppProperties.getTravellersFile(), travellers);

        GUI.getNewUserLabel().setVisible(true);
        GUI.getYes().setVisible(true);
        GUI.getNo().setVisible(true);
    }

    private City getSuggestedCity(Traveller traveller, String weather) {
        if (!manyTravellers) {
            return traveller.CompareCities(weather, cityObjects);
        }

        List<RecommendedCity> recommendations =
                CollaborativeFiltering.getRecommendations(travellers, traveller);
        if (!recommendations.isEmpty()) {
            String[] cityParts = recommendations.getFirst().getCity().split(", ");
            return new City(cityParts[0], cityParts[1]);
        }
        return null;
    }

    @Override public void mousePressed(MouseEvent e) { /* No action needed */ }
    @Override public void mouseReleased(MouseEvent e) { /* No action needed */ }
    @Override public void mouseEntered(MouseEvent e) { /* No action needed */ }
    @Override public void mouseExited(MouseEvent e) { /* No action needed */ }

    /**
     * Parses and validates a traveller's birthdate.
     *
     * @param date the date as a string
     * @return true if the format is valid, false otherwise
     */
    public static boolean checkBirthDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            dateFormat.parse(date);
        } catch (ParseException ex) {
            GUI.setErrorInputMessage("Wrong Date Format!");
            return false;
        }
        return true;
    }

    /**
     * Validates and splits the base city input into name and country parts.
     *
     * @param baseCity   the base city string
     * @param checkFlags a list of validation flags
     * @return an array containing city name and country
     */
    public static String[] checkBaseCity(String baseCity, ArrayList<Boolean> checkFlags) {
        String[] bCity;
        try {
            bCity = baseCity.split(", ");
        } catch (ArrayIndexOutOfBoundsException ex) {
            GUI.setErrorInputMessage("Wrong Base City Format!");
            checkFlags.add(false);
            return new String[]{""};
        }

        if (bCity.length != 2) {
            GUI.setErrorInputMessage("Wrong Base City Format!");
            checkFlags.add(false);
        }
        return bCity;
    }

    /**
     * Validates user-entered city names, constructs {@link City} objects,
     * and stores them in collections.
     *
     * @param guiCities   raw city text input
     * @param cities      collection of city names
     * @param cityObjects collection of {@link City} instances
     * @param APP_ID      the API key
     * @return true if all cities are valid and added successfully
     */
    public static boolean checkCities(
            String guiCities,
            ArrayList<String> cities,
            ArrayList<City> cityObjects,
            final String APP_ID) {

        String[] userCities = guiCities.split("(?<=\\\\G\\\\S,\\\\S),");

        for (String candidateCity : userCities) {
            String[] cityString = candidateCity.split(", ");
            if (cityString.length < 2) {
                GUI.setErrorInputMessage("Format: City_name, City_country (First 2-3 letters)");
                return false;
            }

            City cityObject = new City(cityString[0], cityString[1]);
            cityObject.configureCity(APP_ID);

            if (cityObjects.contains(cityObject)) {
                GUI.setErrorInputMessage("The city is already given to the system");
                return false;
            }

            if (!cityObjects.add(cityObject) || !cities.add(candidateCity)) {
                GUI.setErrorInputMessage("Check again!");
                return false;
            }
        }
        return true;
    }

    /**
     * Validates traveller criteria input.
     *
     * @param kind                        the type of traveller
     * @param criterias                   raw criteria string
     * @param criteriaSuggestionsOfCustomer the validated list of criteria
     * @return true if valid, false otherwise
     */
    public static boolean checkTravellerCriterias(
            int kind, String criterias, ArrayList<String> criteriaSuggestionsOfCustomer) {

        if (kind == 2) return true;

        String[] userCriteria = criterias.split(", ");
        for (String criteria : userCriteria) {
            if (criteriaSuggestionsOfCustomer.contains(criteria)) {
                GUI.setErrorInputMessage("Duplicate criterias!");
                return false;
            } else if (!criteriaSuggestionsOfCustomer.add(criteria)) {
                GUI.setErrorInputMessage("Check again!");
                return false;
            }
        }
        return true;
    }
}
