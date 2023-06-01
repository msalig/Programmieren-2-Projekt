package net.salig.lagerspiel.controller_real;

import net.salig.lagerspiel.model_real.Auftragseingang;
import net.salig.lagerspiel.view_real.AuftragseingangView;

public class AuftragseingangControllerImpl implements AuftragseingangController {
    private final Auftragseingang model;
    private final AuftragseingangView view;

    public AuftragseingangControllerImpl(Auftragseingang model, AuftragseingangView view) {
        this.model = model;
        this.view = view;

        model.setController(this);
        view.setController(this);
    }

    @Override
    public void handleNeuerAuftrag() {
        model.handleNeuerAuftrag();
    }
}