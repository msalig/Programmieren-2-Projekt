package net.salig.lagerspiel.controller;

import net.salig.lagerspiel.model.Action;
import net.salig.lagerspiel.model.Produkt;

import javax.swing.*;
import java.util.ArrayList;

public class Bilanz {

    private int kontostand;

    private final ArrayList<Produkt> transaktionen = new ArrayList<>();

    private JLabel label = new JLabel();

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public void auftragAbschließen(Produkt produkt, Action action) {
        switch (action) {
            case Einlagerung, Auslagerung -> this.kontostand += produkt.getPrice();
            case Ablehnen -> this.kontostand -= produkt.getPrice();
            case Verschrotten -> this.kontostand -= 300;
        }

        produkt.setAction(action);
        transaktionen.add(produkt);
        updateKontostandLabel();
    }

    private void updateKontostandLabel() {
        label.setText("<html><font color='white' size='15'>Kontostand: " + this.kontostand + "€</font></html>");
    }

    public ArrayList<Produkt> getTransaktionen() {
        return transaktionen;
    }
}
