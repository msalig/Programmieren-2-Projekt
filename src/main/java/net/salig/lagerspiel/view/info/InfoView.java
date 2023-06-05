package net.salig.lagerspiel.view.info;

import net.salig.lagerspiel.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InfoView extends JPanel {

    private static final int PANEL_WIDTH = 96;
    private static final int PANEL_HEIGHT = 96;
    private static final int X = 1314;
    private static final int Y = 30;

    public InfoView() {
        setLocation(new Point(X, Y));
        setSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 125));
        setLayout(new BorderLayout());

        addViews();
    }

    private void addViews() {
        JLabel infoLabel = new JLabel(Utils.createImageIcon("assets/info.png"));
        infoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                showInfoDialog();
            }
        });

        add(infoLabel, BorderLayout.CENTER);
    }

    private void showInfoDialog() {
        JFrame infoFrame = new InfoWindow();
        infoFrame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
