package net.salig.lagerspiel.view.components;

import net.salig.lagerspiel.model.Product;

import javax.swing.*;

public class StorageArea extends JLabel {
    private Product product;

    public JLabel getLabel() {
        return lagerplatzLabel;
    }

    private final JLabel lagerplatzLabel = new JLabel();

    public StorageArea() {
        this.setHorizontalAlignment(SwingUtilities.CENTER);
        lagerplatzLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    public Product getProdukt() {
        return this.product;
    }

    public void setProdukt(Product product) {
        this.product = product;
    }

    public void setLagerplatzLabelText(String text) {
        lagerplatzLabel.setText(text);
    }

    public void setLagerplatzLabelIcon(ImageIcon icon) {
        lagerplatzLabel.setIcon(icon);
    }

    public void emptyStellplatz() {
        setIcon(null);
        setProdukt(null);
        lagerplatzLabel.setText(null);
        lagerplatzLabel.setIcon(null);
    }
}
