package net.salig.lagerspiel.view;

import net.salig.lagerspiel.Utils;
import net.salig.lagerspiel.controller.Bilanz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KontostandView extends JPanel {

    private final JLabel kontostandLabel = new JLabel("<html><font color='white' size='15'>Kontostand: 0€</font></html>");

    private final Bilanz bilanz;

    public KontostandView(Bilanz bilanz) {
        this.bilanz = bilanz;
        bilanz.setLabel(kontostandLabel);
        setOpaque(false);
        setSize(400,100);
        setLocation(13,30);
        setBackground(new Color(0, 0, 0, 125));

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        addViews();
    }

    private void addViews() {
        JLabel open_bilanz_window = new JLabel(Utils.createImageIcon("bilanz.png"));
        open_bilanz_window.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new BilanzWindow(bilanz);
            }
        });
        add(open_bilanz_window);

        add(kontostandLabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
