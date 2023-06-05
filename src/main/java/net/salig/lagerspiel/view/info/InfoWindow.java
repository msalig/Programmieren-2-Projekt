package net.salig.lagerspiel.view.info;

import net.salig.lagerspiel.Utils;

import javax.swing.*;

public class InfoWindow extends JFrame {

    public InfoWindow() {
        super(Utils.getStringResources().getString("info.window.title"));
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new JLabel("<html><font size='15'>" + Utils.getStringResources().getString("info.window.heading") + "</font></html>"));
    }
}
