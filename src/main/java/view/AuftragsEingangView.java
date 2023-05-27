package main.java.view;

import main.java.controller.AuftragsEingang;
import main.java.draganddrop.AuftragsEingangTransferHandler;
import main.java.draganddrop.DragMouseAdapter;
import main.java.draganddrop.VerschrottenTransferHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AuftragsEingangView extends JPanel {

    private final AuftragsEingang auftragsEingang = new AuftragsEingang();

    public AuftragsEingangView() {
        setSize(400, 500);
        setLocation(13, 330);
        setOpaque(false); //Even if the default maybe false, this line is needed to avoid graphics glitch
        setBackground(new Color(255, 255, 255, 40));
        setLayout(new GridLayout(3, 2, 10, 10));

        JButton neuer_auftrag = new JButton(new ImageIcon(new ImageIcon("src/main/resources/assets/pngonebyone/plus.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("trashcan.png"));
        icon = new ImageIcon(icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        JLabel verschrotten = new JLabel(icon, SwingConstants.CENTER);
        verschrotten.setPreferredSize(new Dimension(400, 400));
        verschrotten.setTransferHandler(new VerschrottenTransferHandler());
        verschrotten.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("trashcanopen.png"));
                icon = new ImageIcon(icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                verschrotten.setIcon(icon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("trashcan.png"));
                icon = new ImageIcon(icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                verschrotten.setIcon(icon);
            }
        });

        add(neuer_auftrag);
        add(verschrotten);

        for (int i = 0; i < 4; i++) {
            auftragsEingang.stellplaetze[i].setTransferHandler(new AuftragsEingangTransferHandler());
            auftragsEingang.stellplaetze[i].addMouseListener(new DragMouseAdapter());
            auftragsEingang.stellplaetze[i].setName(String.valueOf(i));
            add(auftragsEingang.stellplaetze[i]);
        }

        neuer_auftrag.addActionListener(e -> auftragsEingang.getNeuerAuftrag());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public static class Label extends JLabel {
        private String[] information;

        public Label() {
            setHorizontalAlignment(SwingUtilities.CENTER);
        }

        public String[] getInformation() {
            return information;
        }

        public void setInformation(String[] information) {
            this.information = information;
        }
    }
}
