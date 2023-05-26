package main.java.view;

import main.java.draganddrop.DragMouseAdapter;
import main.java.draganddrop.AuftragsEingangTransferHandler;
import main.java.model.Orders;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class AuftragsEingangView extends JPanel {

    Orders orders = new Orders();

    public AuftragsEingangView() {
        setSize(400, 500);
        setLocation(13, 330);
        setOpaque(false); //Even if the default maybe false, this line is needed to avoid graphics glitch
        setBackground(new Color(255, 255, 255, 40));
        setLayout(new GridLayout(3, 2, 10, 10));

        JButton neuer_auftrag = new JButton("Neuer Auftrag");
        JButton verschrotten = new JButton("Verschrotten");

        add(neuer_auftrag);
        add(verschrotten);

        Label[] stellplaetze = {new Label(), new Label(), new Label(), new Label()};

        for (int i = 0; i < 4; i++) {
            stellplaetze[i].setTransferHandler(new AuftragsEingangTransferHandler());
            stellplaetze[i].addMouseListener(new DragMouseAdapter());
            stellplaetze[i].setName(String.valueOf(i));
            add(stellplaetze[i]);
        }

        neuer_auftrag.addActionListener(e -> {
            int freierStellplatz = 0;
            while(freierStellplatz < 4 && !stellplaetze[freierStellplatz].getText().isEmpty()) {
                freierStellplatz++;
            }
            if (freierStellplatz < 4) {
                String[] newOrder = orders.getNextOrder();

                stellplaetze[freierStellplatz].setText(Arrays.deepToString(newOrder));
                stellplaetze[freierStellplatz].setInformation(newOrder);
            }
        });


        /*ImageIcon icon = new ImageIcon("src/main/resources/assets/pngonebyone/down.png");
        icon = new ImageIcon(icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        JLabel label = new JLabel(icon, SwingConstants.CENTER);
        label.addMouseListener(new DragMouseAdapter());

        label.setTransferHandler(new TransferHandler("icon"));
        add(label);*/
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
