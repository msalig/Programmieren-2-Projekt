package main.java.model;

import main.java.view.AuftragsEingangView;

public class AuftragDTO {
    private final AuftragsEingangView.Label source;
    private final Auftrag auftrag;

    public AuftragDTO(AuftragsEingangView.Label source, Auftrag auftrag) {
        this.source = source;
        this.auftrag = auftrag;
    }

    public AuftragsEingangView.Label getSource() {
        return source;
    }

    public Auftrag getAuftrag() {
        return auftrag;
    }
}