package net.salig.lagerspiel.view_real;

import net.salig.lagerspiel.Utils;
import net.salig.lagerspiel.controller.Bilanz;
import net.salig.lagerspiel.controller_real.AuftragseingangController;
import net.salig.lagerspiel.draganddrop.AuftragTransferHandler;
import net.salig.lagerspiel.draganddrop.DragMouseAdapter;
import net.salig.lagerspiel.model_real.Auftragseingang;
import net.salig.lagerspiel.view.components.VerschrottenLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AuftragseingangView extends JPanel {
    private final Auftragseingang model;

    private AuftragseingangController controller;
    private final Bilanz bilanz;

    private JLabel neuer_auftrag;

    public AuftragseingangView(Auftragseingang model, Bilanz bilanz) {
        this.bilanz = bilanz;
        this.model = model;

        initializeUI();
        setupEventListeners();
    }

    public void setController(AuftragseingangController controller) {
        this.controller = controller;
    }

    private void initializeUI() {
        setSize(400, 600);
        setLocation(13, 230);
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 125));
        setLayout(new GridLayout(4, 2, 10, 10));

        neuer_auftrag = new JLabel(Utils.createImageIcon("plus.png"));
        add(neuer_auftrag);
        add(new VerschrottenLabel(bilanz));

        for (int i = 0; i < 3; i++) {
            model.getStellplatz(i).setTransferHandler(new AuftragTransferHandler(bilanz));
            model.getStellplatz(i).addMouseListener(new DragMouseAdapter());
            model.getStellplatz(i).setName("Auftragseingang_" + i);
            add(model.getStellplatz(i).getLagerplatzLabel());
            add(model.getStellplatz(i));
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
        if (model.isPaintTruck()) {
            g.drawImage(Utils.createImageIcon("truck.png", 200, 200).getImage(), this.getWidth() / 2, this.getHeight() / 4 * model.getAuslagerStellplatz(), this);
        }
    }
}