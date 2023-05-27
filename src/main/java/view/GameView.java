package main.java.view;

import main.java.controller.Bilanz;

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

        background = new ImageIcon("src/main/resources/assets/background.jpg");
        background.setImage(background.getImage().getScaledInstance(1440, 900, Image.SCALE_SMOOTH));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background.getImage(), 0, 0, this);
    }
}
