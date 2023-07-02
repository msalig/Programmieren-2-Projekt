/*
 * Copyright (c) 2023 Marko Salig.
 *
 * Licensed under the MIT license: https://opensource.org/licenses/MIT
 * Permission is granted to use, copy, modify, and redistribute the work.
 * Full license information available in the project LICENSE file.
 */

package net.salig.lagerspiel.controller;

import net.salig.lagerspiel.model.OrderIntake;
import net.salig.lagerspiel.view.OrderIntakeView;

public class OrderIntakeControllerImpl implements OrderIntakeController {
    private final OrderIntake model;
    private final OrderIntakeView view;

    public OrderIntakeControllerImpl(OrderIntake model, OrderIntakeView view) {
        this.model = model;
        this.view = view;

        view.setController(this);
    }

    @Override
    public void handleNewOrder() {
        model.handleNewOrder();
    }
}