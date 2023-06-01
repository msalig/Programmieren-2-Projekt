package net.salig.lagerspiel.view;

import net.salig.lagerspiel.controller.Bilanz;
import net.salig.lagerspiel.draganddrop.AuftragTransferHandler;
import net.salig.lagerspiel.draganddrop.DragMouseAdapter;
import net.salig.lagerspiel.view.components.Lagerplatz;

import javax.swing.*;
import java.awt.*;

public class RegalView extends JPanel {

    private static final int WIDTH = 700;
    private static final int HEIGHT = 800;
    private static final int X = 550;
    private static final int Y = 30;

    public RegalView(Bilanz bilanz) {
        setSize(WIDTH,HEIGHT);
        setLocation(X,Y);
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
