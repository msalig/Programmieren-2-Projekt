package net.salig.lagerspiel;

import net.salig.lagerspiel.view.GameView;

import javax.swing.*;
import java.awt.*;

public class LagerSpiel {

    private static final int WIDTH = 1440;
    private static final int HEIGHT = 900;

    public static void startApplication() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(null, "Failed to set the look and feel.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        LagerSpiel lagerSpiel = new LagerSpiel();
        lagerSpiel.initUI();
    }

    private void initUI() {
        JFrame frame = new JFrame("Lager-Spiel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setResizable(false);
        frame.setIconImage(null);

        frame.setContentPane(new GameView());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
