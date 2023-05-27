package main.java.view;

import main.java.controller.Bilanz;
import main.java.draganddrop.RegalTransferHandler;

import javax.swing.*;
import java.awt.*;

public class RegalView extends JPanel {

    public RegalView(Bilanz bilanz) {
        setSize(700,800);
        setLocation(640,30);
        setOpaque(false); //Even if the default maybe false, this line is needed to avoid graphics glitch
        setBackground(new Color(255, 255, 255, 40));

        GridLayout gridLayout = new GridLayout(4, 4);
        setLayout(gridLayout);
        setBorder(BorderFactory.createEmptyBorder(2,2,2,2));

        for(int i = 0; i < 16; i++) {
            JLabel label = new JLabel("", SwingConstants.CENTER);
            label.setName(String.valueOf(i));
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            label.setTransferHandler(new RegalTransferHandler(bilanz));
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
