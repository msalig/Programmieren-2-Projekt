package main.java.controller;

import javax.swing.*;

public class Bilanz {

    private int bilanz;

    private JLabel label = new JLabel();

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public void setBilanz(int newBilanz) {
        this.bilanz += newBilanz;
        label.setText("Bilanz: " + bilanz + "€");
    }
}
