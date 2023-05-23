import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {

    public static final Dimension PREFERREDSIZE = new Dimension(1440,900);

    private static final long serialVersionUID = 1L;

    public GameView() {
        super("Lager-Spiel");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(PREFERREDSIZE);
        setPreferredSize(PREFERREDSIZE);

        //Center the frame
        setLocationRelativeTo(null);
        //Display the window.
        pack();
        setVisible(true);


        JPanel menu = new Menu();

        setContentPane(menu);
    }
}
