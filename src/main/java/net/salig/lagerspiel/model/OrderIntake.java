/*
 * Copyright (c) 2023 Marko Salig.
 *
 * Licensed under the MIT license: https://opensource.org/licenses/MIT
 * Permission is granted to use, copy, modify, and redistribute the work.
 * Full license information available in the project LICENSE file.
 */

package net.salig.lagerspiel.model;

import net.salig.lagerspiel.view.components.StorageArea;
import net.salig.lagerspiel.Utils;

public class OrderIntake {
    private final Orders orders = new Orders();
    private final StorageArea[] stellplaetze = {new StorageArea(), new StorageArea(), new StorageArea()};

    public void handleNewOrder() {
        int freierStellplatz = 0;
        while (freierStellplatz < stellplaetze.length && stellplaetze[freierStellplatz].getIcon() != null) {
            freierStellplatz++;
        }

        if (freierStellplatz < stellplaetze.length) {
            String[] newOrder = orders.getNextOrder();
            Product newProduct = new Product(newOrder);

            if (newProduct.getAction() == Action.RETRIEVE) {
                stellplaetze[freierStellplatz].setStorageAreaLabelIcon(Utils.createImageIcon(newProduct.getIconPath(), 100, 100));
                stellplaetze[freierStellplatz].setIcon(Utils.createImageIcon("assets/truck.png", 120, 120));
            } else {
                stellplaetze[freierStellplatz].setIcon(Utils.createImageIcon(newProduct.getIconPath(), Utils.IMAGE_SIZE, Utils.IMAGE_SIZE));
            }

            stellplaetze[freierStellplatz].setStorageAreaLabelText("<html><font color='white' size='4'>" + Utils.getString(newProduct.getAction().toString().toLowerCase()) + ", " + newOrder[5] + "€</font></html>");
            stellplaetze[freierStellplatz].setProdukt(new Product(newOrder));
        }
    }

    public StorageArea getStellplatz(int index) {
        return stellplaetze[index];
    }
}