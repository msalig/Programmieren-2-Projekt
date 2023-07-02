package net.salig.lagerspiel.controller_real;

import net.salig.lagerspiel.model_real.OrderIntake;
import net.salig.lagerspiel.view_real.OrderIntakeView;

public class OrderIntakeControllerImpl implements OrderIntakeController {
    private final OrderIntake model;
    private final OrderIntakeView view;

    public OrderIntakeControllerImpl(OrderIntake model, OrderIntakeView view) {
        this.model = model;
        this.view = view;

        view.setController(this);
    }

    @Override
    public void handleNeuerAuftrag() {
        model.handleNeuerAuftrag();
    }
}