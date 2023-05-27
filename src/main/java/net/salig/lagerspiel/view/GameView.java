package net.salig.lagerspiel.view;

import net.salig.lagerspiel.Utils;
import net.salig.lagerspiel.controller.Bilanz;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {

    ImageIcon background;

    Bilanz bilanz = new Bilanz();

    public GameView() {
        setLayout(null);
        setSize(1440, 900);

        addViews();
    }

    private void addViews() {
        add(new AuftragsEingangView());
        add(new RegalView(bilanz));
        add(new BilanzView(bilanz));

        background = Utils.createImageIcon("background.jpg", 1440, 900);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background.getImage(), 0, 0, this);
    }
}
