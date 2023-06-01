package net.salig.lagerspiel.view;

import net.salig.lagerspiel.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InfoView extends JPanel {

    public InfoView() {
        setLocation(1314, 30);
        setSize(96, 96);
        setBackground(Color.DARK_GRAY);

        addViews();
    }

    private void addViews() {
        JLabel info = new JLabel(Utils.createImageIcon("info.png"));
        info.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                JFrame info = new JFrame();
                info.setSize(400, 400);
                info.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                info.setLocationRelativeTo(null);
                info.setVisible(true);
            }
        });

        add(info);
    }
}
