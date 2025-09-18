package com.bellias.gui;

import com.bellias.exception.WikipediaNoArticleException;
import com.bellias.travellerguide.City;
import com.bellias.travellerguide.Traveller;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * The Construction of a class that shows user all travellers and free ticket winner according to
 * event. Implements MouseListener Interface.
 *
 * @author Panagiotis Bellias
 */
public class ResultsFrame implements MouseListener {

    private static JDialog resultsDialog;
    private static JTable travellersTable;
    private static JScrollPane jsp2;
    private static JLabel freeTicketLabel;

    private final String APP_ID;
    private final ArrayList<Traveller> travellers;

    // =======================================================ResultsFrame()=====================================================
    /**
     * The constructor initializes all the necessary fields with specific values.
     *
     * @param APP_ID our OpenWeatherMap API id.
     * @param travellers the ArrayList of Traveller objects where users are kept.
     */
    // ==========================================================================================================================
    public ResultsFrame(String APP_ID, ArrayList<Traveller> travellers) {

      this.APP_ID = APP_ID;
      this.travellers = travellers;
    }

    // ===================================================End of ResultsFrame()================================================

    // Getters and setters

    /**
     * @return the Jsp2
     */
    public static JScrollPane getJsp2() {
      return jsp2;
    }

    /**
     * @param jsp2 the JScrollPane for travellers.
     */
    public static void setJsp2(JScrollPane jsp2) {
      ResultsFrame.jsp2 = jsp2;
    }

    // =======================================================mouseClicked()=====================================================
    /**
     * The method is implemented so as to catch mouse-clicked event and shows user all travellers and
     * free ticket winner.
     *
     * @param e the event which gets caught when happens.
     */
    // ==========================================================================================================================
    @Override
    public void mouseClicked(MouseEvent e) {
      JFrame f = new JFrame();
      resultsDialog = new JDialog(f, "Results", true);
      resultsDialog.setLayout(new FlowLayout());

      // JTable
      // Set ages...
      Traveller.setAgesForAll(travellers);

      // Present travellers sorted by age without dublicates...
      ArrayList<Traveller> sortedList = new ArrayList();
      travellersAfterSorting(travellers, sortedList);
      setTravellersToTable(sortedList);

      // FreeTicket
      freeTicketLabel = new JLabel();
      freeTicketLabel.setBounds(10, 50, 160, 100);

      try {
        // Free ticket...
        freeTicket(travellers, "Athens", "GR", APP_ID); // Method to handle free ticket
      } catch (IOException | WikipediaNoArticleException ex) {
        System.out.println(ex);
      }

      JButton exitButton = new JButton("Exit");
      exitButton.setBounds(200, 520, 60, 40);
      resultsDialog.add(exitButton);
      exitButton.addActionListener(new CloseListenerClass());

      resultsDialog.setSize(600, 600);
      resultsDialog.setVisible(true);
      resultsDialog.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    // ====================================================End of mouseClicked()=================================================

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

    // ====================================================End of mousePressed()=================================================

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

    // ====================================================End of mouseReleased()================================================

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

    // ====================================================End of mouseEntered()=================================================

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

    // ====================================================End of mouseExited()==================================================

    // =================================================travellersAfterSorting()=================================================
    /**
     * The method sets travellers sorted by age without dublicates.
     *
     * @param travellers the travellers given to get sorted by age.
     * @param sortedList the sorted travellers.
     */
    // ==========================================================================================================================
    public static void travellersAfterSorting(
        ArrayList<Traveller> travellers, ArrayList<Traveller> sortedList) {

      Collections.sort(travellers);

      for (Traveller traveller : travellers) {
        if (!sortedList.contains(traveller)) sortedList.add(traveller);
      }
    }

    // =============================================End of travellersAfterSorting()==============================================

    // ==================================================setTravellersToTable()==================================================
    /**
     * The method puts travellers into JTable.
     *
     * @param travellers the travellers who must be putted into the JTable.
     */
    // ==========================================================================================================================
    public static void setTravellersToTable(ArrayList<Traveller> travellers) {

      // Part VVII: Print all travellers output...
      // Creating JTable
      final String[] columnNames = {"Full name", "Age"};

      int arraySize = travellers.size();
      Object[][] sorted = new Object[arraySize][2];

      Iterator<Traveller> tr = travellers.iterator();
      int i = 0;
      while (tr.hasNext()) {
        Traveller t = tr.next();
        sorted[i][0] = t.getName();
        sorted[i][1] = t.getAge();
      }

      final DefaultTableModel dtm = new DefaultTableModel(sorted, columnNames);
      travellersTable = new JTable(dtm);
      jsp2 = new JScrollPane();
      jsp2.getViewport().add(travellersTable);
      jsp2.setBounds(10, 10, 400, 10 * travellers.size());
      jsp2.setVisible(true);
      resultsDialog.add(jsp2);
    }

    // =============================================End of setTravellersToTable()===============================================

    // ======================================================freeTicket()=======================================================
    /**
     * The method sets freeTicketLabel for a given city, applying polymorphism.
     *
     * @param travellers the travellers who exist in the system.
     * @param name the city name
     * @param country the city's country
     * @param APP_ID our OpenWeatherMap API id.
     * @throws java.io.IOException
     * @throws com.bellias.exception.WikipediaNoArticleException
     */
    // ==========================================================================================================================
    public static void freeTicket(
        ArrayList<Traveller> travellers, String name, String country, final String APP_ID)
        throws IOException, WikipediaNoArticleException {

      City city = new City();
      city.setCityName(name);
      city.setCityCountry(country);
      city.configureCity(APP_ID);

      Traveller qualifiedCustomer = Traveller.findQualifiedCustomer(travellers, city);

      // Handle NullPointerException...
      try {
        freeTicketLabel.setText(
            "City "
                + city.getCityName()
                + ", "
                + city.getCityCountry()
                + " gives free ticket in User "
                + qualifiedCustomer.getCustomerID()
                + ": "
                + qualifiedCustomer.getName());
      } catch (NullPointerException e) {
        freeTicketLabel.setText("There are no travellers to the system!");
      }

      freeTicketLabel.setVisible(true);
      resultsDialog.add(freeTicketLabel);
    }
    // ==================================================End of freeTicket()=====================================================

} // =====================================================End of Class ResultsFrame =================================================
