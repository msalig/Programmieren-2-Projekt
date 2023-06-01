package net.salig.lagerspiel.view;

import net.salig.lagerspiel.model.Produkt;

import javax.swing.*;

public class Lagerplatz extends JLabel {
    private Produkt produkt;

    public JLabel getLagerplatzLabel() {
        return lagerplatzLabel;
    }

    private final JLabel lagerplatzLabel = new JLabel();

    public Lagerplatz() {
        this.setHorizontalAlignment(SwingUtilities.CENTER);
        lagerplatzLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    public Produkt getProdukt() {
        return this.produkt;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    public void setLagerplatzLabelText(String text) {
        this.lagerplatzLabel.setText(text);
    }

    public void emptyStellplatz() {
        this.setIcon(null);
        this.setProdukt(null);
        this.lagerplatzLabel.setText(null);
    }
}
