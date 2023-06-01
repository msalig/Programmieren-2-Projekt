package net.salig.lagerspiel.view;

import net.salig.lagerspiel.Utils;
import net.salig.lagerspiel.controller.Bilanz;
import net.salig.lagerspiel.draganddrop.AuftragTransferHandler;
import net.salig.lagerspiel.draganddrop.DragMouseAdapter;

import javax.swing.*;
import java.awt.*;

public class RegalView extends JPanel {

    public RegalView(Bilanz bilanz) {
        setSize(700,800);
        setLocation(550,30);
        setOpaque(false); //Even if the default maybe false, this line is needed to avoid graphics glitch
        setBackground(new Color(0, 0, 0, 125));

        GridLayout gridLayout = new GridLayout(4, 4);
        setLayout(gridLayout);
        setBorder(BorderFactory.createEmptyBorder(2,2,2,2));

        for(int i = 0; i < 16; i++) {
            Lagerplatz label = new Lagerplatz();
            label.setName("Regal_" + i);
            label.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            label.addMouseListener(new DragMouseAdapter());
            label.setTransferHandler(new AuftragTransferHandler(bilanz));
            add(label);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getBackground());
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
