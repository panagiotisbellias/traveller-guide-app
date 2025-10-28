package com.bellias.gui;

import com.bellias.config.AppProperties;
import com.bellias.storage.DataStoreFactory;
import com.bellias.travellerguide.City;
import com.bellias.travellerguide.Traveller;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

/**
 * The Construction of a graphic user interface that supports the app.
 *
 * @author Panagiotis Bellias
 */
public class GUI {

  private static final JFrame FRAME = new JFrame("Traveller Guide App");
  private static final JPanel PANEL = new JPanel();

  private static JLabel kindLabel,
      nameLabel,
      dateLabel,
      bCityLabel,
      citiesLabel,
      criteriaLabel,
      weatherLabel,
      suggestedCityLabel,
      newUserLabel,
      freeTicketLabel,
      suggestedCity;
  private static JComboBox<Integer> dayBox, monthBox, yearBox;
  private static ButtonGroup options;
  private static JRadioButton st, bt, tour;
  private static int kind;
  private static JTextField nameText, bCityText, weatherText;
  private static TextArea citiesText, criteriaText;
  private static JToggleButton tb1, tb2, tb3;
  private static ButtonGroup buttonGroup;
  private static JButton yes, no;

  private static String errorInputMessage;
  private static JLabel errorLabel;

  /* The button for the most popular city */
  private static JButton mpc;

  // Cities...
  static ArrayList<String> cities = new ArrayList<>();
  static ArrayList<City> cityObjects = new ArrayList<>();

  static final String APP_ID = "2a24f0970630ea181d5daf393bf4615b"; // OpenWeatherMap API id
  static ArrayList<Traveller> travellers =
      new ArrayList<>(); // Keep traveller objects in an ArrayList
  static int id; // Traveller id
  static boolean manyTravellers = false; // Choose Filtering-condition

  // ===========================================================GUI()==========================================================
  /**
   * The constructor initializes some fields, designs the GUI and present it to the user.
   *
   * @throws java.io.IOException
   * @throws java.lang.ClassNotFoundException
   */
  // ==========================================================================================================================
  public GUI() throws IOException, ClassNotFoundException {

    travellers = DataStoreFactory.create().loadTravellers(AppProperties.getTravellersFile());
    System.out.println(travellers.size());
    if (!travellers.isEmpty()) {
      Traveller.setTravellersNumber(travellers.size());
    }

    if (Traveller.getTravellersNumber() >= 6) manyTravellers = true;

    // Adjust traveller's id
    id = 1;
    if (Traveller.getTravellersNumber() > 0) // get the id of the last traveller in the arraylist
    id = travellers.get(Traveller.getTravellersNumber() - 1).getCustomerID() + 1;

    // set up the frame and display it
    FRAME.setSize(800, 700);
    FRAME.setLocation(300, 25);
    FRAME.add(PANEL);
    placeComponents(PANEL);
    FRAME.setVisible(true);
    FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  // ======================================================End of
  // GUI()=======================================================

  // =====================================================placeComponents()===================================================
  /**
   * The method places all the needed components into the JPanel.
   *
   * @param panel the JPanel which is added to the frame of GUI.
   */
  // ==========================================================================================================================
  private static void placeComponents(final JPanel panel) {

    panel.setLayout(null);

    JLabel titleLabel = new JLabel("Traveller Guide App");

    kindLabel = new JLabel("Kind"); // Creating JLabel for kind of traveller IO

    options = new ButtonGroup(); // Creating ButtonGroup
    st = new JRadioButton("Simple Traveller");
    bt = new JRadioButton("Business Traveller");
    tour = new JRadioButton("Tourist");
    options.add(st);
    options.add(bt);
    options.add(tour);

    nameLabel = new JLabel("Name"); // Name traveller IO
    nameText = new JTextField();

    dateLabel =
        new JLabel("Birth Date (DD-MM-YYYY)"); // Creating JLabel for birthdate of traveller IO
    String day[] = {
      "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
      "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"
    };
    dayBox = new JComboBox(day);
    String month[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    monthBox = new JComboBox(month);
    String year[] = {
      "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000",
      "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989",
      "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978",
      "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967",
      "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956",
      "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945",
      "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934",
      "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923",
      "1922", "1921"
    };
    yearBox = new JComboBox(year);

    bCityLabel = new JLabel("Base City"); // Creating JLabel for Base city IO
    bCityText = new JTextField(); // Creating JTextField

    criteriaLabel = new JLabel("Criteria"); // Criterias IO
    criteriaText = new TextArea(); // Creating TextArea

    weatherLabel = new JLabel("Weather you dislike"); // Creating JLabel for Weather preferences IO
    weatherText = new JTextField(); // Creating JTextField

    suggestedCityLabel =
        new JLabel("We suggest you: "); // Creating JLabel for Suggested city output
    suggestedCity = new JLabel(); // Creating JLabel

    errorLabel = new JLabel();

    // Creating ToggleButtons to create SUBMIT, CANCEL, HELP buttons
    tb1 = new JToggleButton("SUBMIT");
    tb2 = new JToggleButton("CANCEL");
    tb3 = new JToggleButton("HELP");
    buttonGroup = new ButtonGroup();

    newUserLabel = new JLabel("New User?"); // New user IO
    yes = new JButton("YES");
    no = new JButton("NO");

    /* Create the button for the most popular city */
    mpc = new JButton("MOST POPULAR CITY");

    titleLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
    errorLabel.setForeground(Color.red);

    titleLabel.setBounds(300, 10, 200, 22);
    kindLabel.setBounds(10, 40, 160, 25);
    st.setBounds(200, 40, 165, 25);
    bt.setBounds(375, 40, 165, 25);
    tour.setBounds(550, 40, 165, 25);
    nameLabel.setBounds(10, 70, 160, 25);
    nameText.setBounds(200, 70, 165, 25);
    dateLabel.setBounds(10, 100, 160, 25);
    dayBox.setBounds(200, 100, 40, 20);
    monthBox.setBounds(240, 100, 40, 20);
    yearBox.setBounds(280, 100, 80, 20);
    bCityLabel.setBounds(10, 130, 160, 25);
    bCityText.setBounds(200, 130, 165, 25);

    if (!manyTravellers) {
      citiesLabel = new JLabel("Suggested Cities"); // Creating JLabel for Cities IO
      citiesText = new TextArea(); // Creating TextArea

      citiesLabel.setBounds(10, 160, 160, 25);
      citiesText.setBounds(200, 160, 330, 100);
      criteriaLabel.setBounds(10, 265, 160, 25);
      criteriaText.setBounds(200, 265, 330, 100);

      panel.add(citiesLabel);
      panel.add(citiesText);
    } else {
      criteriaLabel.setBounds(10, 160, 160, 25);
      criteriaText.setBounds(200, 160, 330, 100);
    }

    weatherLabel.setBounds(10, 370, 160, 25);
    weatherText.setBounds(200, 370, 165, 25);
    suggestedCityLabel.setBounds(10, 430, 160, 25);
    suggestedCity.setBounds(200, 430, 165, 25);
    errorLabel.setBounds(200, 400, 330, 25);
    tb1.setBounds(550, 370, 83, 25);
    tb2.setBounds(550, 400, 83, 25);
    tb3.setBounds(550, 430, 83, 25);
    newUserLabel.setBounds(10, 550, 160, 25);
    yes.setBounds(200, 550, 60, 40);
    no.setBounds(280, 550, 60, 40);

    /* Adjust the position for the button that shows the most popular city */
    mpc.setBounds(550, 340, 160, 25);

    st.addActionListener(
        (ActionEvent e1) -> {
          GUI.setKind(1);
        });
    bt.addActionListener(
        (ActionEvent e1) -> {
          GUI.setKind(2);
        });
    tour.addActionListener(
        (ActionEvent e1) -> {
          GUI.setKind(3);
        });

    tb1.addMouseListener(
        new DataProcessing(APP_ID, travellers, cities, cityObjects, id, manyTravellers));
    tb2.addMouseListener(new ClearAreas());
    tb3.addMouseListener(new ShowHelp());
    yes.addMouseListener(new NewUserFrame());
    no.addMouseListener(new ResultsFrame(APP_ID, travellers));

    /* Add Mouse Listener to the new button */
    mpc.addMouseListener(new MostPopularCities());

    suggestedCityLabel.setVisible(false);
    suggestedCity.setVisible(false);
    errorLabel.setVisible(false);
    newUserLabel.setVisible(false);
    yes.setVisible(false);
    no.setVisible(false);

    tb1.setToolTipText("Confirm your data to see results");
    tb2.setToolTipText("Correct your data by erasing them all and after entering them again");
    tb3.setToolTipText("See instructions to use the app");

    /* Setting the toolTip text for the new button */
    mpc.setToolTipText("Press this button to see the most popular city you entered");

    buttonGroup.add(tb1);
    buttonGroup.add(tb2);
    buttonGroup.add(tb3);

    panel.add(titleLabel);
    panel.add(kindLabel);
    panel.add(st);
    panel.add(bt);
    panel.add(tour);
    panel.add(nameLabel);
    panel.add(nameText);
    panel.add(dateLabel);
    panel.add(dayBox);
    panel.add(monthBox);
    panel.add(yearBox);
    panel.add(bCityLabel);
    panel.add(bCityText);
    panel.add(criteriaLabel);
    panel.add(criteriaText);
    panel.add(weatherLabel);
    panel.add(weatherText);
    panel.add(suggestedCityLabel);
    panel.add(suggestedCity);
    panel.add(errorLabel);
    panel.add(tb1);
    panel.add(tb2);
    panel.add(tb3);
    panel.add(yes);
    panel.add(no);

    /* Add the new button to JPanel */
    panel.add(mpc);
  }

  // ================================================End of
  // placeComponents()=================================================

  // Getters and setters

  /* JFrame getter */
  public static JFrame getFRAME() {
    return FRAME;
  }

  /**
   * @return the Panel
   */
  public static JPanel getPanel() {
    return PANEL;
  }

  /**
   * @return the SuggestedCityLabel
   */
  public static JLabel getSuggestedCityLabel() {
    return suggestedCityLabel;
  }

  /**
   * @param suggestedCityLabel the first suggested city JLabel.
   */
  public static void setSuggestedCityLabel(final JLabel suggestedCityLabel) {
    GUI.suggestedCityLabel = suggestedCityLabel;
  }

  /**
   * @return the SuggestedCityText
   */
  public static String getSuggestedCityText() {
    return suggestedCity.getText();
  }

  /**
   * @param suggestedCity the suggested city which is defined from the recommandation algorithms.
   */
  public static void setSuggestedCityText(final String suggestedCity) {
    GUI.suggestedCity.setText(suggestedCity);
  }

  /**
   * @return the newUserLabel
   */
  public static JLabel getNewUserLabel() {
    return newUserLabel;
  }

  /**
   * @param newUserLabel the newUser's JLabel.
   */
  public static void setNewUserLabel(JLabel newUserLabel) {
    GUI.newUserLabel = newUserLabel;
  }

  /**
   * @return the SuggestedCity
   */
  public static JLabel getSuggestedCity() {
    return suggestedCity;
  }

  /**
   * @param suggestedCity the second suggested city JLabel.
   */
  public static void setSuggestedCity(final JLabel suggestedCity) {
    GUI.suggestedCity = suggestedCity;
  }

  /**
   * @return the freeTicketLabel
   */
  public static JLabel getFreeTicketLabel() {
    return freeTicketLabel;
  }

  /**
   * @param freeTicketLabel the freeTicket's JLabel.
   */
  public static void setFreeTicketLabel(JLabel freeTicketLabel) {
    GUI.freeTicketLabel = freeTicketLabel;
  }

  /**
   * @return the DayBox's content
   */
  public static String getDayBox() {
    return dayBox.getSelectedItem().toString();
  }

  /**
   * @param day the day of user's birth date.
   */
  public static void setDayBox(String day) {
    GUI.dayBox.setSelectedItem(day);
  }

  /**
   * @return the MonthBox's content
   */
  public static String getMonthBox() {
    return monthBox.getSelectedItem().toString();
  }

  /**
   * @param month the month of user's birth date.
   */
  public static void setMonthBox(String month) {
    GUI.monthBox.setSelectedItem(month);
  }

  /**
   * @return the YearBox's content
   */
  public static String getYearBox() {
    return yearBox.getSelectedItem().toString();
  }

  /**
   * @param year the year of user's birth date.
   */
  public static void setYearBox(String year) {
    GUI.yearBox.setSelectedItem(year);
  }

  /**
   * @return the Kind
   */
  public static int getKind() {
    return kind;
  }

  /**
   * @param kind the traveller-kind of user.
   */
  public static void setKind(final int kind) {
    GUI.kind = kind;
  }

  /**
   * @return the NameText
   */
  public static String getNameText() {
    return nameText.getText();
  }

  /**
   * @param nameText the name of user.
   */
  public static void setNameText(final String nameText) {
    GUI.nameText.setText(nameText);
  }

  /**
   * @return the bCityTextField
   */
  public static JTextField getbCityTextField() {
    return bCityText;
  }

  /**
   * @param bCityText the base city's JTextField of user.
   */
  public static void setbCityTextField(final JTextField bCityText) {
    GUI.bCityText = bCityText;
  }

  /**
   * @return the bCityText
   */
  public static String getbCityText() {
    return bCityText.getText();
  }

  /**
   * @param bCityText the base city of user.
   */
  public static void setbCityText(final String bCityText) {
    GUI.bCityText.setText(bCityText);
  }

  /**
   * @return the weatherText
   */
  public static String getWeatherText() {
    return weatherText.getText();
  }

  /**
   * @param weatherText the weather preferences of user.
   */
  public static void setWeatherText(final String weatherText) {
    GUI.weatherText.setText(weatherText);
  }

  /**
   * @return the citiesTextArea
   */
  public static TextArea getCitiesTextArea() {
    return citiesText;
  }

  /**
   * @param citiesText the cities' TextArea.
   */
  public static void setCitiesTextArea(final TextArea citiesText) {
    GUI.citiesText = citiesText;
  }

  /**
   * @return the citiesText
   */
  public static String getCitiesText() {
    return citiesText.getText();
  }

  /**
   * @param citiesText the cities user wants.
   */
  public static void setCitiesText(final String citiesText) {
    GUI.citiesText.setText(citiesText);
  }

  /**
   * @return the criteriaText
   */
  public static String getCriteriaText() {
    return criteriaText.getText();
  }

  /**
   * @param criteriaText the criteria user wants.
   */
  public static void setCriteriaText(final String criteriaText) {
    GUI.criteriaText.setText(criteriaText);
  }

  /**
   * @return the yes JButton
   */
  public static JButton getYes() {
    return yes;
  }

  /**
   * @param yes the yes JButton.
   */
  public static void setYes(JButton yes) {
    GUI.yes = yes;
  }

  /**
   * @return the no JButton
   */
  public static JButton getNo() {
    return no;
  }

  /**
   * @param no the no JButton.
   */
  public static void setNo(JButton no) {
    GUI.no = no;
  }

  public static String getErrorInputMessage() {
    return errorInputMessage;
  }

  public static void setErrorInputMessage(String errorInputMessage) {
    GUI.errorInputMessage = errorInputMessage;
  }

  public static JLabel getErrorLabel() {
    return errorLabel;
  }

  public static void setErrorLabel(JLabel errorLabel) {
    GUI.errorLabel = errorLabel;
  }
} // ======================================================End of Class
  // GUI======================================================
