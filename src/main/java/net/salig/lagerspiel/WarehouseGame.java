package net.salig.lagerspiel;

import net.salig.lagerspiel.view.GameView;

import javax.swing.*;
import java.awt.*;

public class WarehouseGame {

    private static final int WIDTH = 1440;
    private static final int HEIGHT = 900;

    public static void startApplication() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(null,
                    Utils.getStringResources().getString("error.message.look_and_feel"),
                    Utils.getStringResources().getString("error.title.look_and_feel"),
                    JOptionPane.ERROR_MESSAGE);
        }

        WarehouseGame warehouseGame = new WarehouseGame();
        warehouseGame.initUI();
    }

    private void initUI() {
        JFrame frame = new JFrame(Utils.getStringResources().getString("main_window_title"));
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
