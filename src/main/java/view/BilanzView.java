package main.java.view;

import javax.swing.*;
import java.awt.*;

public class BilanzView extends JPanel {

    public BilanzView() {
        setSize(150,150);
        setLocation(13,30);
        setBackground(new Color(0, 0, 0, 40));

        JButton open_bilanz_window = new JButton("Bilanz");
        add(open_bilanz_window);
        open_bilanz_window.addActionListener(e -> new BilanzWindow());
    }
}
