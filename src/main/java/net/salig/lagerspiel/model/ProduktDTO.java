package net.salig.lagerspiel.model;

import net.salig.lagerspiel.view.components.Lagerplatz;

public class ProduktDTO {
    private final Lagerplatz source;
    private final Produkt produkt;

    public ProduktDTO(Lagerplatz source, Produkt produkt) {
        this.source = source;
        this.produkt = produkt;
    }

    public Lagerplatz getSource() {
        return source;
    }

    public Produkt getAuftrag() {
        return produkt;
    }
}