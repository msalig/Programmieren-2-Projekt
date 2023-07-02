/*
 * Copyright (c) 2023 Marko Salig.
 *
 * Licensed under the MIT license: https://opensource.org/licenses/MIT
 * Permission is granted to use, copy, modify, and redistribute the work.
 * Full license information available in the project LICENSE file.
 */

package net.salig.lagerspiel.view.components;

import net.salig.lagerspiel.model.Product;

import javax.swing.*;

public class StorageArea extends JLabel {
    private Product product;

    public JLabel getLabel() {
        return storageAreaLabel;
    }

    private final JLabel storageAreaLabel = new JLabel();

    public StorageArea() {
        this.setHorizontalAlignment(SwingUtilities.CENTER);
        storageAreaLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    public Product getProdukt() {
        return this.product;
    }

    public void setProdukt(Product product) {
        this.product = product;
    }

    public void setStorageAreaLabelText(String text) {
        storageAreaLabel.setText(text);
    }

    public void setStorageAreaLabelIcon(ImageIcon icon) {
        storageAreaLabel.setIcon(icon);
    }

    public void empty() {
        setIcon(null);
        setProdukt(null);
        storageAreaLabel.setText(null);
        storageAreaLabel.setIcon(null);
    }
}
