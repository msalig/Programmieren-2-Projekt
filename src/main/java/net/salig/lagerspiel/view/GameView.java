package net.salig.lagerspiel.view;

import net.salig.lagerspiel.Utils;
import net.salig.lagerspiel.controller.Bilanz;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {

    private Bilanz bilanz;
    private ImageIcon background;

    public GameView() {
        bilanz = new Bilanz();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1440, 900));

        loadBackgroundImage();
        addViews();
    }

    private void loadBackgroundImage() {
        background = Utils.createImageIcon("background.jpg");
    }

    private void addViews() {
        add(createKontostandView(), BorderLayout.NORTH);
        add(createAuftragseingangView(), BorderLayout.WEST);
        add(createRegalView(), BorderLayout.CENTER);
        add(new InfoView(), BorderLayout.EAST);
    }

    private KontostandView createKontostandView() {
        return new KontostandView(bilanz);
    }

    private AuftragseingangView createAuftragseingangView() {
        return new AuftragseingangView(bilanz);
    }

    private RegalView createRegalView() {
        return new RegalView(bilanz);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}