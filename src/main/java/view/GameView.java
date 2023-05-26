package main.java.view;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {

    ImageIcon background;

    public GameView() {
        setLayout(null);
        setSize(1400, 900);

        add(new AuftragsEingangView());
        add(new Regal());
        add(new BilanzView());

        background = new ImageIcon("src/main/resources/assets/background.jpg");
        background.setImage(background.getImage().getScaledInstance(1440,900,Image.SCALE_SMOOTH));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background.getImage(), 0, 0, this);
    }
}
