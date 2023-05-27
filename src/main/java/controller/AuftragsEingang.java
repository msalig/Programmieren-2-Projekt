package main.java.controller;

import main.java.model.Orders;
import main.java.view.AuftragsEingangView;

import java.util.Arrays;

public class AuftragsEingang {

    private final Orders orders = new Orders();

    public AuftragsEingangView.Label[] stellplaetze = {new AuftragsEingangView.Label(), new AuftragsEingangView.Label(), new AuftragsEingangView.Label(), new AuftragsEingangView.Label()};

    public void getNeuerAuftrag() {
        int freierStellplatz = 0;
        while (freierStellplatz < 4 && !stellplaetze[freierStellplatz].getText().isEmpty()) {
            freierStellplatz++;
        }
        if (freierStellplatz < 4) {
            String[] newOrder = orders.getNextOrder();

            stellplaetze[freierStellplatz].setText(Arrays.deepToString(newOrder));
            stellplaetze[freierStellplatz].setInformation(newOrder);
        }
    }
}
