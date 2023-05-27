package main.java.view;

import main.java.controller.Bilanz;

import javax.swing.*;
import java.awt.*;

public class BilanzView extends JPanel {

    JLabel bilanzLabel = new JLabel("Bilanz: 0€");

    public BilanzView(Bilanz bilanz) {
        bilanz.setLabel(bilanzLabel);
        setSize(150,150);
        setLocation(13,30);

        addViews();
    }

    private void addViews() {
        JButton open_bilanz_window = new JButton("Open Bilanz Window");
        open_bilanz_window.addActionListener(e -> new BilanzWindow());
        add(open_bilanz_window);

        add(bilanzLabel);
    }
}
