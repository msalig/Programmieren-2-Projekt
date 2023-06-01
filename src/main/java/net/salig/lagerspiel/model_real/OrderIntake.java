package net.salig.lagerspiel.model_real;

import net.salig.lagerspiel.model.Orders;
import net.salig.lagerspiel.view.components.StorageArea;
import net.salig.lagerspiel.model.Product;
import net.salig.lagerspiel.model.Action;
import net.salig.lagerspiel.Utils;

public class OrderIntake {
    private final Orders orders = new Orders();
    private final StorageArea[] stellplaetze = {new StorageArea(), new StorageArea(), new StorageArea()};

    public void handleNeuerAuftrag() {
        int freierStellplatz = 0;
        while (freierStellplatz < stellplaetze.length && stellplaetze[freierStellplatz].getIcon() != null) {
            freierStellplatz++;
        }
        if (freierStellplatz < stellplaetze.length) {
            String[] newOrder = orders.getNextOrder();
            Product newProduct = new Product(newOrder);

            if (newProduct.getAction() == Action.Auslagerung) {
                stellplaetze[freierStellplatz].setLagerplatzLabelIcon(Utils.createImageIcon(newProduct.getIconPath(), 120, 120));
                stellplaetze[freierStellplatz].setIcon(Utils.createImageIcon("assets/truck.png", Utils.IMAGE_SIZE, Utils.IMAGE_SIZE));
            } else {
                stellplaetze[freierStellplatz].setIcon(Utils.createImageIcon(newProduct.getIconPath(), Utils.IMAGE_SIZE, Utils.IMAGE_SIZE));
            }
            stellplaetze[freierStellplatz].setLagerplatzLabelText("<html><font color='white' size='4'>" + newProduct.getAction() + ", " + newOrder[5] + "€</font></html>");
            stellplaetze[freierStellplatz].setProdukt(new Product(newOrder));
        }
    }

    public StorageArea getStellplatz(int index) {
        return stellplaetze[index];
    }
}