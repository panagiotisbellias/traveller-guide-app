package com.bellias.gui;

import com.bellias.config.AppProperties;
import com.bellias.exception.WikipediaNoArticleException;
import com.bellias.opendata.weather.OpenWeatherMap;
import com.bellias.rest.WeatherThread;
import com.bellias.storage.DataStoreFactory;
import com.bellias.travellerguide.Business;
import com.bellias.travellerguide.City;
import com.bellias.travellerguide.CollaborativeFiltering;
import com.bellias.travellerguide.Tourist;
import com.bellias.travellerguide.Traveller;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * The Construction of a class that runs the whole program according to event. Implements
 * MouseListener Interface.
 *
 * @author Panagiotis Bellias
 */
public class DataProcessing implements MouseListener {

  final String APP_ID;
  ArrayList<Traveller> travellers;
  ArrayList<String> cities;
  ArrayList<City> cityObjects;
  int id;
  boolean manyTravellers;

  ArrayList<Boolean> checkFlags = new ArrayList<>();

  // =======================================================DataProcessing()=====================================================
  /**
   * The constructor initializes all the necessary fields for the execution of the program with
   * specific values.
   *
   * @param APP_ID our OpenWeatherMap API id.
   * @param travellers the ArrayList of Traveller objects where users are kept.
   * @param cities the cities that traveller wants in String format (just name and country).
   * @param cityObjects the cities that traveller wants in Objects.
   * @param id the traveller's id.
   * @param manyTravellers specifies which recommandation algorithm will be used to suggest city to
   *     a traveller. If true, the collaborative filtering will be used. If false, the content-based
   *     filtering will be used.
   */
  // ==========================================================================================================================
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

  // ===================================================End of
  // DataProcessing()================================================

  // =======================================================mouseClicked()=====================================================
  /**
   * The method is implemented so as to catch mouse-clicked event and executes all of the program.
   *
   * @param e the event which gets caught when happens.
   */
  // ==========================================================================================================================
  @Override
  public void mouseClicked(MouseEvent e) {

    int kind = GUI.getKind();

    String name = GUI.getNameText();
    String date = GUI.getDayBox() + "-" + GUI.getMonthBox() + "-" + GUI.getYearBox();

    Date birthDate = new Date();
    checkFlags.add(checkBirthDate(date, birthDate));

    String baseCity = GUI.getbCityText();
    String[] bCity;
    bCity = checkBaseCity(baseCity, checkFlags);

    String GUIcities = GUI.getCitiesText();
    checkFlags.add(checkCities(GUIcities, cities, cityObjects, APP_ID));

    String criteria = GUI.getCriteriaText();
    ArrayList<String> criteriaSuggestionsOfCustomer = new ArrayList<>();
    checkFlags.add(checkTravellerCriterias(kind, criteria, criteriaSuggestionsOfCustomer));

    // check condition
    //        if(checkFlags.contains(false)){
    GUI.getErrorLabel().setText(GUI.getErrorInputMessage());
    GUI.getErrorLabel().setVisible(true);
    //            return;
    //        }

    String bName = bCity[0];
    String bCountry = bCity[1];

    /* Retrive data from OpenWeatherMapAPI */
    WeatherThread wt = new WeatherThread("weatherThread", bName, bCountry, APP_ID);
    wt.start();

    while (wt.getOpenWeatherMapObject() == null) {
      System.out.println("Data loading");
    }

    OpenWeatherMap weatherObject = wt.getOpenWeatherMapObject();

    double lat = 0.00, lon = 0.00;
    try {
      lat = weatherObject.getCoord().getLat();
      lon = weatherObject.getCoord().getLon();
    } catch (NullPointerException ex) {
      System.out.println(ex);
    }

    String weather = GUI.getWeatherText();

    // Suggest the most-similar city
    City suggestedCity = new City(); // make a City object
    switch (kind) {
      case 1:
        {
          Traveller traveller =
              new Traveller(
                  name,
                  birthDate,
                  lat,
                  lon,
                  criteriaSuggestionsOfCustomer,
                  cities,
                  id); // defining the traveller
          travellers.add(traveller);
          if (!manyTravellers)
            suggestedCity =
                traveller.CompareCities(weather, cityObjects); // comparing cities to find the best
          else
            suggestedCity =
                CollaborativeFiltering.collaborativeFilteringMethod(travellers, traveller);

          try {
            traveller.setVisit(suggestedCity.getCityName() + ", " + suggestedCity.getCityCountry());
          } catch (NullPointerException ex) {
            traveller.setVisit(null);
          }
          break;
        }
      case 2:
        {
          Business traveller =
              new Business(name, birthDate, lat, lon, new ArrayList<>(), cities, id);
          // defining the traveller
          travellers.add(traveller);
          if (!manyTravellers)
            suggestedCity =
                traveller.CompareCities(weather, cityObjects); // comparing cities to find the best
          else
            suggestedCity =
                CollaborativeFiltering.collaborativeFilteringMethod(travellers, traveller);

          try {
            traveller.setVisit(suggestedCity.getCityName() + ", " + suggestedCity.getCityCountry());
          } catch (NullPointerException ex) {
            traveller.setVisit(null);
          }
          break;
        }
      case 3:
        {
          Tourist traveller =
              new Tourist(
                  name,
                  birthDate,
                  lat,
                  lon,
                  criteriaSuggestionsOfCustomer,
                  cities,
                  id); // defining the traveller
          travellers.add(traveller);
          if (!manyTravellers)
            suggestedCity =
                traveller.CompareCities(weather, cityObjects); // comparing cities to find the best
          else
            suggestedCity =
                CollaborativeFiltering.collaborativeFilteringMethod(travellers, traveller);

          try {
            traveller.setVisit(suggestedCity.getCityName() + ", " + suggestedCity.getCityCountry());
          } catch (NullPointerException ex) {
            traveller.setVisit(null);
          }
          break;
        }
      default:
        break;
    }

    City.setSuggestedCityToGUI(suggestedCity);
    if (suggestedCity != null) {
      GUI.getSuggestedCity().setVisible(true);
      GUI.getSuggestedCityLabel().setVisible(true);
      GUI.getNewUserLabel().setVisible(true);
    } else System.out.println(suggestedCity + ": null");

    // delete all contents from the arraylists for the next user
    cities.clear();
    cityObjects.clear();

    GUI.getNewUserLabel().setVisible(true); // new user

    DataStoreFactory.create().saveTravellers(AppProperties.getTravellersFile(), travellers);

    GUI.getNewUserLabel().setVisible(true);
    GUI.getYes().setVisible(true);
    GUI.getNo().setVisible(true);
  }

  // ====================================================End of
  // mouseClicked()=================================================

  // =======================================================mousePressed()=====================================================
  /**
   * The method is implemented so as to catch mouse-pressed event and executes many commands.
   * Auto-generated method stub.
   *
   * @param e the event which gets caught when happens.
   */
  // ==========================================================================================================================
  @Override
  public void mousePressed(MouseEvent e) {}

  // ====================================================End of
  // mousePressed()=================================================

  // =======================================================mouseReleased()====================================================
  /**
   * The method is implemented so as to catch mouse-released event and executes many commands.
   * Auto-generated method stub.
   *
   * @param e the event which gets caught when happens.
   */
  // ==========================================================================================================================
  @Override
  public void mouseReleased(MouseEvent e) {}

  // ====================================================End of
  // mouseReleased()================================================

  // =======================================================mouseEntered()=====================================================
  /**
   * The method is implemented so as to catch mouse-entered event and executes many commands.
   * Auto-generated method stub.
   *
   * @param e the event which gets caught when happens.
   */
  // ==========================================================================================================================
  @Override
  public void mouseEntered(MouseEvent e) {}

  // ====================================================End of
  // mouseEntered()=================================================

  // =======================================================mouseExited()======================================================
  /**
   * The method is implemented so as to catch mouse-exited event and executes many commands.
   * Auto-generated method stub.
   *
   * @param e the event which gets caught when happens.
   */
  // ==========================================================================================================================
  @Override
  public void mouseExited(MouseEvent e) {}

  // ====================================================End of
  // mouseExited()==================================================

  // =====================================================checkBirthDate()=====================================================
  /**
   * The method parses date from String to a Date object.
   *
   * @param date the date as String.
   * @param birthDate the date object we want to adjust.
   * @return true or false according the input.
   */
  // ==========================================================================================================================
  public static boolean checkBirthDate(String date, Date birthDate) {

    SimpleDateFormat dateFormat = new SimpleDateFormat("DD-MM-YYYY");

    try {
      birthDate = dateFormat.parse(date);
    } catch (ParseException ex) {
      GUI.setErrorInputMessage("Wrong Date Format!");
      return false;
    }

    return true;
  }

  // ==================================================End of
  // checkBirthDate()=================================================

  // =====================================================checkBaseCity()=====================================================
  /**
   * The method checks base city as proper input.
   *
   * @param baseCity the city we want to check.
   * @param checkFlags the check arraylist of boolean elements.
   * @return the base city as an array of name and country.
   */
  // ==========================================================================================================================
  public static String[] checkBaseCity(String baseCity, ArrayList<Boolean> checkFlags) {

    String[] bCity = {""};

    try {
      bCity = baseCity.split(", ");
    } catch (ArrayIndexOutOfBoundsException ex) {
      GUI.setErrorInputMessage("Wrong Base City Format!");
      checkFlags.add(false);
    }

    if (bCity.length != 2) {
      GUI.setErrorInputMessage("Wrong Base City Format!");
      checkFlags.add(false);
    }

    return bCity;
  }

  // ==================================================End of
  // checkBaseCity()=================================================

  // ======================================================checkCities()=======================================================
  /**
   * The method checks the cities given by the user and fills the collections.
   *
   * @param cities the cities that traveller wants in String format (just name and country).
   * @param cityObjects the cities that traveller wants in Objects.
   * @param APP_ID our OpenWeatherMap API id.
   * @param GUIcities the cities user entered in the TextArea.
   * @return true or false according input.
   */
  // ==========================================================================================================================

  public static boolean checkCities(
      String GUIcities,
      ArrayList<String> cities,
      ArrayList<City> cityObjects,
      final String APP_ID) {

    String[] userCities = GUIcities.split("(?<=\\\\G\\\\S,\\\\S),");

    for (String candidateCity : userCities) {

      String[] cityString = candidateCity.split(", ");
      if (cityString.length < 2) {
        GUI.setErrorInputMessage("Format: City_name, City_country(First 2-3 letters)");
        return false;
      }
      City cityObject = new City(cityString[0], cityString[1]);
      try {
        cityObject.configureCity(APP_ID);
      } catch (WikipediaNoArticleException | IOException ex) {
        GUI.setErrorInputMessage("There isn't such city. Try again!");
        return false;
      }
      // See if we have this city in system now...
      if (cityObjects.contains(cityObject)) {
        System.out.println(cityObjects.toArray().toString());
        GUI.setErrorInputMessage("The city is already given to the system");
        return false;
      }

      // If we don't have it we add it to the collections...

      // Save cities as objects and also as Strings...
      if (!cityObjects.add(cityObject) || !cities.add(candidateCity)) {
        GUI.setErrorInputMessage("Check again!");
        return false;
      }
    }

    return true;
  }

  // ==================================================End of
  // checkCities()=================================================

  // ==============================================checkTravellerCriterias()================================================
  /**
   * The method checks the cities given by the user and fills the collections.
   *
   * @param kind the kind of traveller defined by user's choice.
   * @param criterias the criterias user entered in TextArea.
   * @param criteriaSuggestionsOfCustomer the traveller's criteria for access.
   * @return true or false according input.
   */
  // =======================================================================================================================
  public static boolean checkTravellerCriterias(
      int kind, String criterias, ArrayList<String> criteriaSuggestionsOfCustomer) {

    if (kind != 2) {

      String[] userCriterias = criterias.split(", ");

      for (String criteria : userCriterias) {
        if (criteriaSuggestionsOfCustomer.contains(criteria)) {
          GUI.setErrorInputMessage("Dublicate criterias!");
          return false;
        } else if (!criteriaSuggestionsOfCustomer.add(criteria)) {
          GUI.setErrorInputMessage("Check again");
          return false;
        }
      }
    }

    return true;
  }
  // ============================================End of
  // checkTravellerCriterias()===========================================

} // ==================================================End of Class
  // DataProcessing==============================================
