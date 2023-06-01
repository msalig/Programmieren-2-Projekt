package net.salig.lagerspiel.controller;

import net.salig.lagerspiel.Utils;
import net.salig.lagerspiel.model.Action;
import net.salig.lagerspiel.model.Produkt;
import net.salig.lagerspiel.model.Orders;
import net.salig.lagerspiel.view.components.Lagerplatz;

public class Auftragseingang {

    private final Orders orders = new Orders();

    public Lagerplatz[] stellplaetze = {new Lagerplatz(), new Lagerplatz(), new Lagerplatz()};

    public int stellplatz = 0;
    public boolean paintTruck = false;

    public void getNeuerAuftrag() {
        int freierStellplatz = 0;
        while (freierStellplatz < stellplaetze.length && stellplaetze[freierStellplatz].getIcon() != null) {
            freierStellplatz++;
        }
        if (freierStellplatz < stellplaetze.length) {
            String[] newOrder = orders.getNextOrder();
            Produkt newProdukt = new Produkt(newOrder);
            if(newProdukt.getAction() == Action.Auslagerung) {
                paintTruck = true;
                stellplatz = freierStellplatz;
            }


            stellplaetze[freierStellplatz].setIcon(Utils.createImageIcon(newProdukt.getIconPath(), Utils.IMAGE_SIZE, Utils.IMAGE_SIZE));
            stellplaetze[freierStellplatz].setLagerplatzLabelText("<html><font color='white' size='4'>" + newProdukt.getAction() + ", " + newOrder[5] + "€</font></html>");
            stellplaetze[freierStellplatz].setProdukt(new Produkt(newOrder));
        }
    }
}
