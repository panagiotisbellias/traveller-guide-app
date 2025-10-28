package com.bellias.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* Taken code by stackoverflow */

public class CloseListenerClass implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent e) {
    // DO SOMETHING
    System.exit(0);
  }
}
