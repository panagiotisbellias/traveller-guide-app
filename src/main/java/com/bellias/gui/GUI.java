package com.bellias.gui;

import com.bellias.config.AppProperties;
import com.bellias.storage.DataStoreFactory;
import com.bellias.travellerguide.City;
import com.bellias.travellerguide.Traveller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Constructs and manages the graphical user interface of the Traveller Guide App.
 *
 * <p>This class initializes and displays all UI components and provides accessors for them.
 *
 * @author Panagiotis Bellias
 */
public class GUI {

    private static JFrame frame;
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
    private static JComboBox dayBox;
    private static JComboBox monthBox;
    private static JComboBox yearBox;
    private static JRadioButton st, bt, tour;
    private static int kind;
    private static JTextField nameText, baseCityText, weatherText;
    private static TextArea citiesText, criteriaText;
    private static JToggleButton tb1, tb2, tb3;
    private static JButton yes, no, mpc;
    private static String errorInputMessage;
    private static JLabel errorLabel;

    static ArrayList<String> cities = new ArrayList<>();
    static ArrayList<City> cityObjects = new ArrayList<>();
    static ArrayList<Traveller> travellers = new ArrayList<>();

    static final String APP_ID = "2a24f0970630ea181d5daf393bf4615b";
    static int id;
    static boolean manyTravellers = false;

    /** Initializes and displays the GUI components. */
    public GUI() {
        travellers = DataStoreFactory.create().loadTravellers(AppProperties.getTravellersFile());
        System.out.println(travellers.size());

        if (!travellers.isEmpty()) {
            Traveller.setTravellersNumber(travellers.size());
        }

        manyTravellers = Traveller.getTravellersNumber() >= 6;
        id = travellers.isEmpty()
                ? 1
                : travellers.get(Traveller.getTravellersNumber() - 1).getCustomerID() + 1;

        if (!GraphicsEnvironment.isHeadless()) {
            frame = new JFrame("Traveller Guide App");
            frame.setSize(800, 700);
            frame.setLocation(300, 25);
            frame.add(PANEL);
            placeComponents();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } else {
            System.out.println("Running in headless mode: GUI not initialized.");
        }
    }

    /** Places all components into the main panel. */
    private static void placeComponents() {
        ButtonGroup buttonGroup;
        GUI.PANEL.setLayout(null);

        JLabel titleLabel = new JLabel("Traveller Guide App");
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        titleLabel.setBounds(300, 10, 200, 22);

        kindLabel = new JLabel("Kind");
        ButtonGroup options = new ButtonGroup();
        st = new JRadioButton("Simple Traveller");
        bt = new JRadioButton("Business Traveller");
        tour = new JRadioButton("Tourist");
        options.add(st);
        options.add(bt);
        options.add(tour);

        nameLabel = new JLabel("Name");
        nameText = new JTextField();

        dateLabel = new JLabel("Birth Date (DD-MM-YYYY)");
        String[] day = createSequenceStrings(1, 31);
        String[] month = createSequenceStrings(1, 12);
        String[] year = createSequenceStrings(1921, 2010);
        dayBox = new JComboBox(day);
        monthBox = new JComboBox(month);
        yearBox = new JComboBox(year);

        bCityLabel = new JLabel("Base City");
        baseCityText = new JTextField();

        criteriaLabel = new JLabel("Criteria");
        criteriaText = new TextArea();

        weatherLabel = new JLabel("Weather you dislike");
        weatherText = new JTextField();

        suggestedCityLabel = new JLabel("We suggest you:");
        suggestedCity = new JLabel();
        errorLabel = new JLabel();
        errorLabel.setForeground(Color.red);

        tb1 = new JToggleButton("SUBMIT");
        tb2 = new JToggleButton("CANCEL");
        tb3 = new JToggleButton("HELP");
        buttonGroup = new ButtonGroup();
        buttonGroup.add(tb1);
        buttonGroup.add(tb2);
        buttonGroup.add(tb3);

        newUserLabel = new JLabel("New User?");
        yes = new JButton("YES");
        no = new JButton("NO");
        mpc = new JButton("MOST POPULAR CITY");

        positionComponents();

        st.addActionListener((ActionEvent e1) -> setKind(1));
        bt.addActionListener((ActionEvent e1) -> setKind(2));
        tour.addActionListener((ActionEvent e1) -> setKind(3));

        tb1.addMouseListener(
                new DataProcessing(APP_ID, travellers, cities, cityObjects, id, manyTravellers));
        tb2.addMouseListener(new ClearAreas());
        tb3.addMouseListener(new ShowHelp());
        yes.addMouseListener(new NewUserFrame());
        no.addMouseListener(new ResultsFrame(APP_ID, travellers));
        mpc.addMouseListener(new MostPopularCities());

        suggestedCityLabel.setVisible(false);
        suggestedCity.setVisible(false);
        errorLabel.setVisible(false);
        newUserLabel.setVisible(false);
        yes.setVisible(false);
        no.setVisible(false);

        tb1.setToolTipText("Confirm your data to see results");
        tb2.setToolTipText("Erase data and re-enter them");
        tb3.setToolTipText("View usage instructions");
        mpc.setToolTipText("See the most popular city entered");

        addComponents(titleLabel);
    }

    /** Utility method to create number strings from a start to an end value. */
    private static String[] createSequenceStrings(int start, int end) {
        String[] result = new String[end - start + 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = String.valueOf(start + i);
        }
        return result;
    }

    /** Defines all component bounds and layout details. */
    private static void positionComponents() {
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
        baseCityText.setBounds(200, 130, 165, 25);

        if (!manyTravellers) {
            citiesLabel = new JLabel("Suggested Cities");
            citiesText = new TextArea();
            citiesLabel.setBounds(10, 160, 160, 25);
            citiesText.setBounds(200, 160, 330, 100);
        }

        criteriaLabel.setBounds(10, manyTravellers ? 160 : 265, 160, 25);
        criteriaText.setBounds(200, manyTravellers ? 160 : 265, 330, 100);
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
        mpc.setBounds(550, 340, 160, 25);
    }

    /** Adds all visible components to the panel. */
    private static void addComponents(JLabel titleLabel) {
        GUI.PANEL.add(titleLabel);
        GUI.PANEL.add(kindLabel);
        GUI.PANEL.add(st);
        GUI.PANEL.add(bt);
        GUI.PANEL.add(tour);
        GUI.PANEL.add(nameLabel);
        GUI.PANEL.add(nameText);
        GUI.PANEL.add(dateLabel);
        GUI.PANEL.add(dayBox);
        GUI.PANEL.add(monthBox);
        GUI.PANEL.add(yearBox);
        GUI.PANEL.add(bCityLabel);
        GUI.PANEL.add(baseCityText);
        GUI.PANEL.add(criteriaLabel);
        GUI.PANEL.add(criteriaText);
        GUI.PANEL.add(weatherLabel);
        GUI.PANEL.add(weatherText);
        GUI.PANEL.add(suggestedCityLabel);
        GUI.PANEL.add(suggestedCity);
        GUI.PANEL.add(errorLabel);
        GUI.PANEL.add(tb1);
        GUI.PANEL.add(tb2);
        GUI.PANEL.add(tb3);
        GUI.PANEL.add(yes);
        GUI.PANEL.add(no);
        GUI.PANEL.add(mpc);
        if (!manyTravellers) {
            GUI.PANEL.add(citiesLabel);
            GUI.PANEL.add(citiesText);
        }
    }

    // Getters and setters follow below, all simplified for clarity.

    public static JFrame getFRAME() {
        return frame;
    }

    public static JPanel getPanel() {
        return PANEL;
    }

    public static JLabel getSuggestedCityLabel() {
        return suggestedCityLabel;
    }

    public static void setSuggestedCityLabel(JLabel suggestedCityLabel) {
        GUI.suggestedCityLabel = suggestedCityLabel;
    }

    public static String getSuggestedCityText() {
        return suggestedCity.getText();
    }

    public static void setSuggestedCityText(String suggestedCity) {
        GUI.suggestedCity.setText(suggestedCity);
    }

    public static JLabel getNewUserLabel() {
        return newUserLabel;
    }

    public static void setNewUserLabel(JLabel newUserLabel) {
        GUI.newUserLabel = newUserLabel;
    }

    public static JLabel getSuggestedCity() {
        return suggestedCity;
    }

    public static void setSuggestedCity(JLabel suggestedCity) {
        GUI.suggestedCity = suggestedCity;
    }

    public static JLabel getFreeTicketLabel() {
        return freeTicketLabel;
    }

    public static void setFreeTicketLabel(JLabel freeTicketLabel) {
        GUI.freeTicketLabel = freeTicketLabel;
    }

    public static String getDayBox() {
        return Objects.requireNonNull(dayBox.getSelectedItem()).toString();
    }

    public static void setDayBox(String day) {
        GUI.dayBox.setSelectedItem(day);
    }

    public static String getMonthBox() {
        return Objects.requireNonNull(monthBox.getSelectedItem()).toString();
    }

    public static void setMonthBox(String month) {
        GUI.monthBox.setSelectedItem(month);
    }

    public static String getYearBox() {
        return Objects.requireNonNull(yearBox.getSelectedItem()).toString();
    }

    public static void setYearBox(String year) {
        GUI.yearBox.setSelectedItem(year);
    }

    public static int getKind() {
        return kind;
    }

    public static void setKind(int kind) {
        GUI.kind = kind;
    }

    public static String getNameText() {
        return nameText.getText();
    }

    public static void setNameText(String nameText) {
        GUI.nameText.setText(nameText);
    }

    public static String getBaseCityText() {
        return baseCityText.getText();
    }

    public static void setBaseCityText(String baseCityText) {
        GUI.baseCityText.setText(baseCityText);
    }

    public static String getWeatherText() {
        return weatherText.getText();
    }

    public static void setWeatherText(String weatherText) {
        GUI.weatherText.setText(weatherText);
    }

    public static String getCitiesText() {
        return citiesText.getText();
    }

    public static void setCitiesText(String citiesText) {
        GUI.citiesText.setText(citiesText);
    }

    public static String getCriteriaText() {
        return criteriaText.getText();
    }

    public static void setCriteriaText(String criteriaText) {
        GUI.criteriaText.setText(criteriaText);
    }

    public static JButton getYes() {
        return yes;
    }

    public static void setYes(JButton yes) {
        GUI.yes = yes;
    }

    public static JButton getNo() {
        return no;
    }

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
}
