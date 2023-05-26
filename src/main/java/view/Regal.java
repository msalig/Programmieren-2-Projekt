package main.java.view;

import main.java.draganddrop.RegalTransferHandler;

import javax.swing.*;
import java.awt.*;

public class Regal extends JPanel {

    public Regal() {
        setSize(700,800);
        setLocation(640,30);
        setOpaque(false); //Even if the default maybe false, this line is needed to avoid graphics glitch
        setBackground(new Color(255, 255, 255, 40));

        GridLayout gridLayout = new GridLayout(4, 4);
        setLayout(gridLayout);
        setBorder(BorderFactory.createEmptyBorder(2,2,2,2));

        for(int i = 0; i < 16; i++) {
            JLabel label = new JLabel("", SwingConstants.CENTER);

            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            label.setTransferHandler(new RegalTransferHandler());
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
