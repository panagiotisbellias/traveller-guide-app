package com.bellias.gui;

import com.bellias.config.AppProperties;
import com.bellias.storage.DataStoreFactory;
import com.bellias.storage.StorageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Shows application help to the user.
 * Implements MouseListener to respond to mouse events.
 * Author: Panagiotis Bellias
 */
public class ShowHelp implements MouseListener {

    /* ---------------- MouseListener Methods ---------------- */

    /**
     * Handles mouse click events to display the help dialog.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        JDialog helpDialog;
        JFrame frame = new JFrame();
        helpDialog = new JDialog(frame, "Help", true);
        helpDialog.setSize(500, 200);
        helpDialog.setLocation(200, 200);
        helpDialog.setLayout(new FlowLayout());

        // OK button to close the help dialog
        JButton okButton = new JButton("OK");
        okButton.addActionListener((ActionEvent e1) -> helpDialog.setVisible(false));

        // Read help data from storage
        ArrayList<String> data = new StorageUtil(DataStoreFactory.create())
                .read(AppProperties.getHelpFile());

        // Display help labels
        int maxLabels = 5;
        for (int i = 0; i < maxLabels; i++) {
            String text = (i < data.size()) ? data.get(i) : "No help data available.";
            JLabel label = new JLabel(text);
            label.setBounds(10, 10 + i * 20, 400, 20);
            helpDialog.add(label);
        }

        helpDialog.add(okButton);
        helpDialog.setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
