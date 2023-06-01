package net.salig.lagerspiel.model_real;

import net.salig.lagerspiel.controller_real.AuftragseingangController;
import net.salig.lagerspiel.model.Orders;
import net.salig.lagerspiel.view.components.Lagerplatz;
import net.salig.lagerspiel.model.Produkt;
import net.salig.lagerspiel.model.Action;
import net.salig.lagerspiel.Utils;

public class Auftragseingang {
    private final Orders orders = new Orders();
    private final Lagerplatz[] stellplaetze = {new Lagerplatz(), new Lagerplatz(), new Lagerplatz()};
    private int auslagerStellplatz = 0;
    private boolean paintTruck = false;

    private AuftragseingangController controller;

    public void setController(AuftragseingangController controller) {
        this.controller = controller;
    }

    public void handleNeuerAuftrag() {
        int freierStellplatz = 0;
        while (freierStellplatz < stellplaetze.length && stellplaetze[freierStellplatz].getIcon() != null) {
            freierStellplatz++;
        }
        if (freierStellplatz < stellplaetze.length) {
            String[] newOrder = orders.getNextOrder();
            Produkt newProdukt = new Produkt(newOrder);
            if (newProdukt.getAction() == Action.Auslagerung) {
                paintTruck = true;
                auslagerStellplatz = freierStellplatz;
            }

            stellplaetze[freierStellplatz].setIcon(Utils.createImageIcon(newProdukt.getIconPath(), Utils.IMAGE_SIZE, Utils.IMAGE_SIZE));
            stellplaetze[freierStellplatz].setLagerplatzLabelText("<html><font color='white' size='4'>" + newProdukt.getAction() + ", " + newOrder[5] + "€</font></html>");
            stellplaetze[freierStellplatz].setProdukt(new Produkt(newOrder));

            // Notify the controller about the new order
            /*if (controller != null) {
                controller.handleNeuerAuftrag();
            }*/
        }
    }

    public Lagerplatz getStellplatz(int index) {
        return stellplaetze[index];
    }

    public boolean isPaintTruck() {
        return paintTruck;
    }

    public int getAuslagerStellplatz() {
        return auslagerStellplatz;
    }
}