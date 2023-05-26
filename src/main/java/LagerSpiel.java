package main.java;

import main.java.view.GameView;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LagerSpiel extends JFrame {

    public static final Dimension PREFERREDSIZE = new Dimension(1440,900);

    public LagerSpiel() {
        super("Lager-Spiel");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(PREFERREDSIZE);
        setPreferredSize(PREFERREDSIZE);
        setResizable(false);
        setLayout(null);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger logger = Logger.getLogger("Lager");
            logger.log(Level.WARNING, ex.toString());
        }

        setContentPane(new GameView());

        //Center the frame
        setLocationRelativeTo(null);
        //Display the window.
        pack();
        setVisible(true);
    }
}
