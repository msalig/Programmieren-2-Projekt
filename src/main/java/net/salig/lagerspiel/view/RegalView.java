package net.salig.lagerspiel.view;

import net.salig.lagerspiel.controller.Balance;
import net.salig.lagerspiel.draganddrop.OrderTransferHandler;
import net.salig.lagerspiel.draganddrop.DragMouseAdapter;
import net.salig.lagerspiel.view.components.StorageArea;

import javax.swing.*;
import java.awt.*;

public class RegalView extends JPanel {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int X = 464;
    private static final int Y = 30;

    private static final int NUM_ROWS_COLS = 4;
    private static final int BEAM_WIDTH = 20;
    private static final int FLOOR_DEPTH = 20;

    public RegalView(Balance balance) {
        setSize(WIDTH, HEIGHT);
        setLocation(X, Y);
        setOpaque(false); //Even if the default maybe false, this line is needed to avoid graphics glitch
        setBackground(new Color(0, 0, 0, 125));

        GridLayout gridLayout = new GridLayout(4, 4);
        setLayout(gridLayout);

        for (int i = 0; i < 16; i++) {
            StorageArea label = new StorageArea();
            label.setSize(WIDTH / NUM_ROWS_COLS, HEIGHT / NUM_ROWS_COLS);
            label.setHorizontalAlignment(JLabel.LEFT);
            label.setName("Regal_" + i);
            //label.setBorder(new LineBorder(Color.WHITE));
            label.addMouseListener(new DragMouseAdapter());
            label.setTransferHandler(new OrderTransferHandler(balance));
            add(label);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getBackground());
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        //Draw floors
        for (int i = 1; i <= 3; i++) {
            // Define the coordinates of the parallelogram
            int[] xPoints = {0, 0, WIDTH - FLOOR_DEPTH / 2, WIDTH};
            int[] yPoints = {i * HEIGHT / NUM_ROWS_COLS, i * HEIGHT / NUM_ROWS_COLS - FLOOR_DEPTH, i * HEIGHT / NUM_ROWS_COLS - FLOOR_DEPTH, i * HEIGHT / NUM_ROWS_COLS};

            //Fill the parallelogram
            Polygon parallelogram = new Polygon(xPoints, yPoints, 4);
            g.setColor(Color.LIGHT_GRAY);
            g.fillPolygon(parallelogram);

            g.setColor(Color.ORANGE);
            g.fillRect(BEAM_WIDTH / 2, i * HEIGHT / NUM_ROWS_COLS - BEAM_WIDTH / 4, WIDTH, BEAM_WIDTH / 2);
        }

        //Draw support beams
        for (int i = 0; i <= NUM_ROWS_COLS; i++) {
            int x = i * WIDTH / NUM_ROWS_COLS;
            g.setColor(Color.ORANGE);
            g.fillRect(x - BEAM_WIDTH / 2, HEIGHT / 4 - FLOOR_DEPTH / 2, BEAM_WIDTH, HEIGHT);
            g.setColor(Color.BLACK);
            g.drawLine(x, HEIGHT / 4 - FLOOR_DEPTH / 2, x, HEIGHT);
        }
    }
}
