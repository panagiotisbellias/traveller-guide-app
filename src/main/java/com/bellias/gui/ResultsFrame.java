package com.bellias.gui;

import com.bellias.travellerguide.City;
import com.bellias.travellerguide.Traveller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Displays all travellers and the free ticket winner according to an event.
 * Implements MouseListener to respond to mouse events.
 * Author: Panagiotis Bellias
 *
 * @param APP_ID the application ID
 * @param travellers the list of travellers
 */
public record ResultsFrame(String APP_ID, ArrayList<Traveller> travellers) implements MouseListener {

    private static JDialog resultsDialog;
    private static JScrollPane jsp2;
    private static JLabel freeTicketLabel;

    /**
     * Constructor initializes fields with specific values.
     *
     * @param APP_ID     our OpenWeatherMap API ID
     * @param travellers list of Traveller objects
     */
    public ResultsFrame {
    }

    /* Getters and setters */
    public static JScrollPane getJsp2() {
        return jsp2;
    }

    public static void setJsp2(JScrollPane jsp2) {
        ResultsFrame.jsp2 = jsp2;
    }

    /* ---------------- MouseListener Methods ---------------- */

    /**
     * Handles mouse click events and shows all travellers and the free ticket winner.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        JFrame f = new JFrame();
        resultsDialog = new JDialog(f, "Results", true);
        resultsDialog.setLayout(new FlowLayout());

        // Set ages for all travellers
        Traveller.setAgesForAll(travellers);

        // Display sorted travellers without duplicates
        ArrayList<Traveller> sortedList = new ArrayList<>();
        travellersAfterSorting(travellers, sortedList);
        setTravellersToTable(sortedList);

        // Display free ticket
        freeTicketLabel = new JLabel();
        freeTicketLabel.setBounds(10, 50, 400, 100);

        freeTicket(travellers, "Athens", "GR", APP_ID);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(200, 520, 60, 40);
        resultsDialog.add(exitButton);
        exitButton.addActionListener(new CloseListenerClass());

        resultsDialog.setSize(600, 600);
        resultsDialog.setVisible(true);
        resultsDialog.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    /* ---------------- Utility Methods ---------------- */

    /**
     * Sorts travellers by age without duplicates.
     *
     * @param travellers input list of travellers
     * @param sortedList list to populate with sorted travellers
     */
    public static void travellersAfterSorting(ArrayList<Traveller> travellers, ArrayList<Traveller> sortedList) {
        Collections.sort(travellers);
        for (Traveller traveller : travellers) {
            if (!sortedList.contains(traveller)) {
                sortedList.add(traveller);
            }
        }
    }

    /**
     * Populates a JTable with travellers.
     *
     * @param travellers list of travellers to display
     */
    public static void setTravellersToTable(ArrayList<Traveller> travellers) {
        JTable travellersTable;
        String[] columnNames = {"Full name", "Age"};
        Object[][] data = new Object[travellers.size()][2];

        int i = 0;
        for (Traveller t : travellers) {
            data[i][0] = t.getName();
            data[i][1] = t.getAge();
            i++;
        }

        DefaultTableModel dtm = new DefaultTableModel(data, columnNames);
        travellersTable = new JTable(dtm);

        jsp2 = new JScrollPane();
        jsp2.getViewport().add(travellersTable);
        jsp2.setBounds(10, 10, 400, 10 * travellers.size());
        jsp2.setVisible(true);
        resultsDialog.add(jsp2);
    }

    /**
     * Determines and displays the free ticket winner for a given city.
     *
     * @param travellers list of travellers
     * @param name       city name
     * @param country    city country
     * @param APP_ID     OpenWeatherMap API ID
     */
    public static void freeTicket(ArrayList<Traveller> travellers, String name, String country, final String APP_ID) {

        City city = new City();
        city.setCityName(name);
        city.setCityCountry(country);
        city.configureCity(APP_ID);

        Traveller qualifiedCustomer = Traveller.findQualifiedCustomer(travellers, city);

        if (qualifiedCustomer != null) {
            freeTicketLabel.setText(
                    "City " + city.getCityName() + ", " + city.getCityCountry() +
                            " gives free ticket to User " + qualifiedCustomer.getCustomerID() +
                            ": " + qualifiedCustomer.getName());
        } else {
            freeTicketLabel.setText("There are no travellers in the system!");
        }

        freeTicketLabel.setVisible(true);
        resultsDialog.add(freeTicketLabel);
    }
}
