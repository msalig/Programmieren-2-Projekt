import view.GameView;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LagerSpiel extends JFrame {

    public static final Dimension PREFERREDSIZE = new Dimension(1440,900);

    private static final long serialVersionUID = 1L;

    private final Logger logger = Logger.getLogger("Lager");


    public LagerSpiel() {
        super("Lager-Spiel");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(PREFERREDSIZE);
        setPreferredSize(PREFERREDSIZE);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            logger.log(Level.WARNING, ex.toString());
        }


        JPanel gameView = new GameView();
        setContentPane(gameView);

        //Center the frame
        setLocationRelativeTo(null);
        //Display the window.
        pack();
        setVisible(true);
    }
}
