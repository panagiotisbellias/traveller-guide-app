package com.bellias.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A {@link MouseListener} implementation that clears all input areas in the GUI
 * when the user clicks a specific component (e.g. a "Clear" button).
 * This listener resets all form fields in the {@link GUI} class to their default
 * values and hides suggestion or error labels.</p>
 *
 * @author Panagiotis Bellias
 */
public class ClearAreas implements MouseListener {

    /**
     * Handles the mouse click event.
     * When triggered, this method clears all input fields, resets combo boxes,
     * hides labels, and logs the action to the console.</p>
     *
     * @param e the mouse click event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        GUI.setKind(0);
        GUI.setNameText("");
        GUI.setDayBox("1");
        GUI.setMonthBox("1");
        GUI.setYearBox("2010");
        GUI.setBaseCityText("");
        GUI.setCitiesText("");
        GUI.setCriteriaText("");
        GUI.setWeatherText("");
        GUI.getSuggestedCityLabel().setVisible(false);
        GUI.getSuggestedCity().setVisible(false);
        GUI.setSuggestedCityText("");
        GUI.getErrorLabel().setVisible(false);
        System.out.println("Areas cleared.");
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the mouse event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        // No action needed
    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the mouse event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        // No action needed
    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the mouse event
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        // No action needed
    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the mouse event
     */
    @Override
    public void mouseExited(MouseEvent e) {
        // No action needed
    }
}
