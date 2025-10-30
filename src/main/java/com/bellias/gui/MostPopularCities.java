package com.bellias.gui;

import com.bellias.config.AppProperties;
import com.bellias.storage.DataStoreFactory;
import com.bellias.storage.StorageUtil;
import com.bellias.travellerguide.PopularCity;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * The construction of a class that finds and shows the most popular city among user-inputted cities.
 * This class implements {@link MouseListener} and can be attached to GUI components to handle
 * mouse events. When clicked, it evaluates the popularity of cities entered by the user and shows
 * a message dialog with the most popular one.
 * Popular cities are also saved to a file for record-keeping.
 *
 * @author Panagiotis Bellias
 */
public class MostPopularCities implements MouseListener {

    /**
     * Handles the mouse click event to process user-entered cities, calculate popularity, and display
     * the most popular city in a message dialog.
     *
     * @param e the {@link MouseEvent} that triggered this listener
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        PopularCity popularCity = citiesProcessing();

        if (GUI.getCitiesText().isEmpty()) {
            return;
        }

        String[] userCities = GUI.getCitiesText().split("\n");

        for (String city : userCities) {
            String[] cityString = city.split(", ");
            if (cityString.length < 2) return;
        }

        JOptionPane.showMessageDialog(
                GUI.getFRAME(),
                "Η δημοφιλέστερη πόλη που δώσατε!\n"
                        + popularCity.getName()
                        + ", "
                        + popularCity.getCountry());
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    /**
     * Reads cities from the GUI text field, creates {@link PopularCity} objects, calculates their
     * popularity, saves them to a file, and returns the most popular city.
     *
     * @return the {@link PopularCity} with the highest popularity, or an empty {@link PopularCity} if no valid cities are provided
     */
    private static PopularCity citiesProcessing() {

        ArrayList<PopularCity> popularCities = new ArrayList<>();

        String guiCities = GUI.getCitiesText();
        if (guiCities.isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    "Please enter your cities and try again!",
                    "No cities found",
                    JOptionPane.ERROR_MESSAGE);
            return new PopularCity();
        }

        String[] userCities = guiCities.split("\n");

        for (String city : userCities) {

            String[] cityString = city.split(", ");

            if (cityString.length < 2) {
                JOptionPane.showMessageDialog(
                        null,
                        "Not right format\nTry again entering city_name, city_country divided with comma",
                        "Wrong City Input",
                        JOptionPane.ERROR_MESSAGE);
                return new PopularCity();
            }

            PopularCity popularCity = new PopularCity(cityString[0], cityString[1]);
            popularCity.calculatePopularity();
            popularCities.add(popularCity);
        }

        popularCitiesToFile(popularCities);

        return maxPopularity(popularCities);
    }

    /**
     * Saves a list of {@link PopularCity} objects to the file defined in {@link AppProperties}.
     *
     * @param popularCities the list of {@link PopularCity} objects to save
     */
    private static void popularCitiesToFile(ArrayList<PopularCity> popularCities) {

        StorageUtil storage = new StorageUtil(DataStoreFactory.create());
        for (PopularCity city : popularCities) {
            storage.write(
                    AppProperties.getPopularCitiesFile(),
                    city.getName() + ",\t" + city.getCountry() + "\t\t" + city.getPopularity());
        }
    }

    /**
     * Finds the {@link PopularCity} with the highest popularity from a given list.
     *
     * @param popularCities the list of {@link PopularCity} objects to evaluate
     * @return the {@link PopularCity} with the maximum popularity, or an empty {@link PopularCity} if the list is empty
     */
    private static PopularCity maxPopularity(ArrayList<PopularCity> popularCities) {

        PopularCity pc = new PopularCity();

        if (popularCities.size() >= 2)
            for (int i = 0; i < popularCities.size() - 1; i++) {
                if (popularCities.get(i).getPopularity() <= popularCities.get(i + 1).getPopularity()) {
                    pc = popularCities.get(i + 1);
                } else {
                    pc = popularCities.get(i);
                }
            }
        else if (!popularCities.isEmpty()) pc = popularCities.getFirst();

        return pc;
    }

}
