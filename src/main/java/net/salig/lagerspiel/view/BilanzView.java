package net.salig.lagerspiel.view;

import net.salig.lagerspiel.controller.Bilanz;

import javax.swing.*;
import java.awt.*;

public class BilanzView extends JPanel {

    JLabel bilanzLabel = new JLabel("<html><font color='white' size='15'>Bilanz: 0€</font></html>");

    public BilanzView(Bilanz bilanz) {
        bilanz.setLabel(bilanzLabel);
        setSize(400,150);
        setLocation(13,30);
        setBackground(Color.GRAY);

        addViews();
    }

    private void addViews() {
        JButton open_bilanz_window = new JButton("Open Bilanz Window");
        open_bilanz_window.addActionListener(e -> new BilanzWindow());
        add(open_bilanz_window);

        add(bilanzLabel);
    }
}
