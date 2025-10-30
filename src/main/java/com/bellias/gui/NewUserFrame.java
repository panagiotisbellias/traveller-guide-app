package com.bellias.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Handles the "New User" confirmation dialog when the user attempts to add a new user.
 *
 * <p>Implements {@link MouseListener} to respond to mouse events.
 *
 * @author Panagiotis Bellias
 */
public class NewUserFrame implements MouseListener {

    private JDialog newUserDialog;

    /** Handles mouse click events and displays a confirmation dialog for adding a new user. */
    @Override
    public void mouseClicked(MouseEvent e) {
        JFrame frame = new JFrame();
        newUserDialog = new JDialog(frame, "New User", true);
        newUserDialog.setLayout(new FlowLayout());

        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");

        yesButton.addActionListener(
                (ActionEvent e1) -> {
                    newUserDialog.setVisible(false);
                    new ClearAreas();
                });

        noButton.addActionListener(
                (ActionEvent e1) -> newUserDialog.setVisible(false));

        newUserDialog.add(new JLabel("Are you sure you want to add a new user?"));
        newUserDialog.add(yesButton);
        newUserDialog.add(noButton);

        newUserDialog.setSize(400, 200);
        newUserDialog.setVisible(true);
    }

    /** Not used. */
    @Override
    public void mousePressed(MouseEvent e) {}

    /** Not used. */
    @Override
    public void mouseReleased(MouseEvent e) {}

    /** Not used. */
    @Override
    public void mouseEntered(MouseEvent e) {}

    /** Not used. */
    @Override
    public void mouseExited(MouseEvent e) {}
}
