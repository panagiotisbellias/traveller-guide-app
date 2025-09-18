package com.bellias.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

/**
 * The Construction of a class that asks user if he is sure about leaving the app according to
 * event. Implements MouseListener Interface.
 *
 * @author Panagiotis Bellias
 */
public class NewUserFrame implements MouseListener {

    private JDialog newUserDialog;

    // =======================================================mouseClicked()=====================================================
    /**
     * The method is implemented so as to catch mouse-clicked event and asks user if he is sure about
     * leaving the app.
     *
     * @param e the event which gets caught when happens.
     */
    // ==========================================================================================================================
    @Override
    public void mouseClicked(MouseEvent e) {
      JFrame f = new JFrame();
      newUserDialog = new JDialog(f, "New User", true);
      newUserDialog.setLayout(new FlowLayout());
      JButton yesButton = new JButton("Yes");
      JButton noButton = new JButton("No");
      yesButton.addActionListener(
          (ActionEvent e1) -> {
            newUserDialog.setVisible(false);
            ClearAreas clearAreas = new ClearAreas();
          });
      noButton.addActionListener(
          (ActionEvent e1) -> {
            newUserDialog.setVisible(false);
          });
      newUserDialog.add(new JLabel("Are you sure you want to add new user?"));
      newUserDialog.add(yesButton);
      newUserDialog.add(noButton);
      newUserDialog.setSize(400, 200);
      newUserDialog.setVisible(true);
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

} // =====================================================End of Class NewUserFrame=================================================
