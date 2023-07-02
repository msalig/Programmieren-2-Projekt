/*
 * Copyright (c) 2023 Marko Salig.
 *
 * Licensed under the MIT license: https://opensource.org/licenses/MIT
 * Permission is granted to use, copy, modify, and redistribute the work.
 * Full license information available in the project LICENSE file.
 */

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

    private JFrame infoWindow;

    public InfoView() {
        setLocation(new Point(X, Y));
        setSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 125));
        setLayout(new BorderLayout());

        addViews();
        showTutorial();
    }

    private void addViews() {
        JLabel infoLabel = new JLabel(Utils.createImageIcon("assets/info.png"));
        infoLabel.setToolTipText(Utils.getString("tooltip.info"));
        infoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                showTutorial();
            }
        });

        add(infoLabel, BorderLayout.CENTER);
    }

    private void showTutorial() {
        if (infoWindow == null) {
            infoWindow = new TutorialWindow();
            infoWindow.setAlwaysOnTop(true);
        }
        infoWindow.setState(JFrame.NORMAL);
        infoWindow.setLocationRelativeTo(null);
        infoWindow.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
