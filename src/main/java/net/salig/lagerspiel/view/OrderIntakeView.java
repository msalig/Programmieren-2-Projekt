package net.salig.lagerspiel.view_real;

import net.salig.lagerspiel.Utils;
import net.salig.lagerspiel.controller.Balance;
import net.salig.lagerspiel.controller_real.OrderIntakeController;
import net.salig.lagerspiel.draganddrop.OrderTransferHandler;
import net.salig.lagerspiel.draganddrop.DragMouseAdapter;
import net.salig.lagerspiel.model_real.OrderIntake;
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

        initializeUI();
        setupEventListeners();
    }

    public void setController(OrderIntakeController controller) {
        this.controller = controller;
    }

    private void initializeUI() {
        setSize(new Dimension(WIDTH, HEIGHT));
        setLocation(new Point(X, Y));
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 125));
        setLayout(new GridLayout(4, 2));

        neuer_auftrag = new JLabel(Utils.createImageIcon("assets/plus.png"));
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
                    controller.handleNeuerAuftrag();
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