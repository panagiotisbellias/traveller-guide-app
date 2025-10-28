package com.bellias.gui;

import com.bellias.config.AppProperties;
import com.bellias.storage.DataStoreFactory;
import com.bellias.storage.StorageUtil;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 * The Construction of a class that shows app usage to the user according to event. Implements
 * MouseListener Interface.
 *
 * @author Panagiotis Bellias
 */
public class ShowHelp implements MouseListener {

  private JDialog helpDialog;

  // =======================================================mouseClicked()=====================================================
  /**
   * The method is implemented so as to catch mouse-clicked event and executes commands to help user
   * use the app.
   *
   * @param e the event which gets caught when happens.
   */
  // ==========================================================================================================================
  @Override
  public void mouseClicked(MouseEvent e) {

    JFrame f = new JFrame();
    helpDialog = new JDialog(f, "Help", true);
    helpDialog.setSize(500, 500);
    helpDialog.setLocation(200, 200);
    helpDialog.setLayout(new FlowLayout());
    JButton okButton = new JButton("OK");
    okButton.setBounds(40, 20, 100, 100);
    okButton.addActionListener(
        (ActionEvent e1) -> {
          helpDialog.setVisible(false);
        });
    ArrayList<String> data =
        new StorageUtil(DataStoreFactory.create()).read(AppProperties.getHelpFile());

    boolean noCheck = false;
    JLabel label1;
    if (!data.isEmpty()) {
      label1 = new JLabel(data.get(0));
    } else {
      label1 = new JLabel("No help data available."); // fallback text
      noCheck = true;
    }
    label1.setBounds(10, 10, 200, 200);
    helpDialog.add(label1);

    JLabel label2;
    if (!noCheck) {
      if (!data.isEmpty()) {
        label2 = new JLabel(data.get(1));
      } else {
        label2 = new JLabel("No help data available."); // fallback text
        noCheck = true;
      }
      label2.setBounds(10, 20, 200, 200);
      helpDialog.add(label2);
    }

    JLabel label3;
    if (!noCheck) {
      if (!data.isEmpty()) {
        label3 = new JLabel(data.get(2));
      } else {
        label3 = new JLabel("No help data available."); // fallback text
        noCheck = true;
      }
      label3.setBounds(10, 30, 200, 200);
      helpDialog.add(label3);
    }

    JLabel label4;
    if (!noCheck) {
      if (!data.isEmpty()) {
        label4 = new JLabel(data.get(3));
      } else {
        label4 = new JLabel("No help data available."); // fallback text
        noCheck = true;
      }
      label4.setBounds(10, 40, 200, 200);
      helpDialog.add(label4);
    }

    JLabel label5;
    if (!noCheck) {
      if (!data.isEmpty()) {
        label5 = new JLabel(data.get(4));
      } else {
        label5 = new JLabel("No help data available."); // fallback text
      }
      label5.setBounds(10, 50, 200, 200);
      helpDialog.add(label5);
    }

    helpDialog.add(okButton);
    helpDialog.setSize(500, 200);
    helpDialog.setVisible(true);
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

} // ======================================================End of Class ShowHelp
  // ==================================================
