package net.salig.lagerspiel.controller;

import javax.swing.*;

public class Bilanz {

    private int bilanz;

    private JLabel label = new JLabel();

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public void setBilanz(int newBilanz) {
        this.bilanz += newBilanz;
        label.setText("<html><font color='white' size='15'>Bilanz: " + this.bilanz + "€</font></html>");
    }
}
