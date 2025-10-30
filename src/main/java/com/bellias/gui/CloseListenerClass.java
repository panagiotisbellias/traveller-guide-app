package com.bellias.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The construction of a simple {@link ActionListener} that closes the application
 * when triggered.
 * This class can be attached to a button or menu item in a GUI. When the action
 * is performed, the application exits immediately.
 * Note: The implementation currently calls {@link System#exit(int)} with status 0.
 *
 * @author Panagiotis Bellias
 */
public class CloseListenerClass implements ActionListener {

    /**
     * Handles the action event and closes the application.
     *
     * @param e the {@link ActionEvent} that triggered this listener
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // DO SOMETHING
        System.exit(0);
    }

}
