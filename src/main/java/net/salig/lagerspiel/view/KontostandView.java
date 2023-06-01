package net.salig.lagerspiel.view;

import net.salig.lagerspiel.Utils;
import net.salig.lagerspiel.controller.Bilanz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KontostandView extends JPanel {

    private final JLabel kontostandLabel = new JLabel("<html><font color='white' size='15'>Balance: 0€</font></html>");

    private final Bilanz bilanz;

    private static final int WIDTH = 400;
    private static final int HEIGHT = 100;
    private static final int X = 13;
    private static final int Y = 30;

    public KontostandView(Bilanz bilanz) {
        this.bilanz = bilanz;
        bilanz.setLabel(kontostandLabel);
        setOpaque(false);
        setSize(WIDTH, HEIGHT);
        setLocation(X, Y);
        setBackground(new Color(0, 0, 0, 125));

        setLayout(new FlowLayout(FlowLayout.LEFT));

        addViews();
    }

    private void addViews() {
        JLabel bilanzIconLabel = new JLabel(Utils.createImageIcon("bilanz.png"));
        bilanzIconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                openBilanzWindow();
            }
        });
        add(bilanzIconLabel);

        add(kontostandLabel);
    }

    private void openBilanzWindow() {
        BilanzWindow bilanzWindow = new BilanzWindow(bilanz);
        bilanzWindow.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
