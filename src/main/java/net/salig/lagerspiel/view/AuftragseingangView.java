package net.salig.lagerspiel.view;

import net.salig.lagerspiel.Utils;
import net.salig.lagerspiel.controller.Auftragseingang;
import net.salig.lagerspiel.controller.Bilanz;
import net.salig.lagerspiel.draganddrop.AuftragTransferHandler;
import net.salig.lagerspiel.draganddrop.DragMouseAdapter;
import net.salig.lagerspiel.view.components.VerschrottenLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AuftragseingangView extends JPanel {

    private final Auftragseingang auftragsEingang = new Auftragseingang();

    public AuftragseingangView(Bilanz bilanz) {
        setSize(400, 600);
        setLocation(13, 230);
        setOpaque(false); //Even if the default maybe false, this line is needed to avoid graphics glitch
        setBackground(new Color(0, 0, 0, 125));
        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel neuer_auftrag = new JLabel(Utils.createImageIcon("plus.png"));

        add(neuer_auftrag);
        add(new VerschrottenLabel(bilanz));

        for (int i = 0; i < 3; i++) {
            auftragsEingang.stellplaetze[i].setTransferHandler(new AuftragTransferHandler(bilanz));
            auftragsEingang.stellplaetze[i].addMouseListener(new DragMouseAdapter());
            auftragsEingang.stellplaetze[i].setName("Auftragseingang_" + i);
            add(auftragsEingang.stellplaetze[i].getLagerplatzLabel());
            add(auftragsEingang.stellplaetze[i]);
        }

        neuer_auftrag.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                auftragsEingang.getNeuerAuftrag();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        if(auftragsEingang.paintTruck)
            g.drawImage(Utils.createImageIcon("truck.png", 200, 200).getImage(), this.getWidth() / 2, this.getHeight() / 4 * auftragsEingang.stellplatz, this);
    }
}
