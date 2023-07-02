/*
 * Copyright (c) 2023 Marko Salig.
 *
 * Licensed under the MIT license: https://opensource.org/licenses/MIT
 * Permission is granted to use, copy, modify, and redistribute the work.
 * Full license information available in the project LICENSE file.
 */

package net.salig.lagerspiel.view;

import net.salig.lagerspiel.Utils;
import net.salig.lagerspiel.controller.Balance;
import net.salig.lagerspiel.controller.OrderIntakeController;
import net.salig.lagerspiel.draganddrop.OrderTransferHandler;
import net.salig.lagerspiel.draganddrop.DragMouseAdapter;
import net.salig.lagerspiel.model.OrderIntake;
import net.salig.lagerspiel.view.components.StorageArea;
import net.salig.lagerspiel.view.components.ScrapLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrderIntakeView extends JPanel {
    private final OrderIntake model;

    private OrderIntakeController controller;
    private final Balance balance;

    private static final int WIDTH = 400;
    private static final int HEIGHT = 600;
    private static final int X = 13;
    private static final int Y = 230;

    private JLabel neuer_auftrag;

    public OrderIntakeView(OrderIntake model, Balance balance) {
        this.balance = balance;
        this.model = model;

        initializeView();
        addViews();
        setupEventListeners();
    }

    public void setController(OrderIntakeController controller) {
        this.controller = controller;
    }

    private void initializeView() {
        setSize(new Dimension(WIDTH, HEIGHT));
        setLocation(new Point(X, Y));
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 125));
        setLayout(new GridLayout(4, 2));
    }

    private void addViews() {
        neuer_auftrag = new JLabel(Utils.createImageIcon("assets/plus.png"));
        neuer_auftrag.setToolTipText(Utils.getString("tooltip.new_order"));
        add(neuer_auftrag);
        add(new ScrapLabel(balance));

        for (int i = 0; i < 3; i++) {
            StorageArea storageArea = model.getStellplatz(i);

            storageArea.setTransferHandler(new OrderTransferHandler(balance));
            storageArea.addMouseListener(new DragMouseAdapter());
            storageArea.setName("Auftragseingang_" + i);
            storageArea.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            add(storageArea.getLabel());
            add(storageArea);
        }
    }

    private void setupEventListeners() {
        neuer_auftrag.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (controller != null) {
                    controller.handleNewOrder();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}