package main.java.controller;

import main.java.Utils;
import main.java.model.AuftragFactory;
import main.java.model.Orders;
import main.java.view.AuftragsEingangView;

public class AuftragsEingang {

    private final Orders orders = new Orders();

    public AuftragsEingangView.Label[] stellplaetze = {new AuftragsEingangView.Label(), new AuftragsEingangView.Label(), new AuftragsEingangView.Label(), new AuftragsEingangView.Label()};

    public void getNeuerAuftrag() {
        int freierStellplatz = 0;
        while (freierStellplatz < 4 && stellplaetze[freierStellplatz].getIcon() != null) {
            freierStellplatz++;
        }
        if (freierStellplatz < 4) {
            String[] newOrder = orders.getNextOrder();

            stellplaetze[freierStellplatz].setIcon(Utils.createImageIcon(AuftragFactory.createAuftrag(newOrder).getIconPath(), 150, 150));
            stellplaetze[freierStellplatz].setInformation(newOrder);
        }
    }
}
