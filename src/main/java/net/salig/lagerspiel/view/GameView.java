package net.salig.lagerspiel.view;

import net.salig.lagerspiel.Utils;
import net.salig.lagerspiel.controller.Bilanz;
import net.salig.lagerspiel.controller_real.AuftragseingangController;
import net.salig.lagerspiel.controller_real.AuftragseingangControllerImpl;
import net.salig.lagerspiel.model_real.Auftragseingang;
import net.salig.lagerspiel.view_real.AuftragseingangView;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {

    private final Bilanz bilanz;
    private ImageIcon background;

    private static final int WIDTH = 1440;
    private static final int HEIGHT = 900;

    public GameView() {
        bilanz = new Bilanz();
        setLayout(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        loadBackgroundImage();
        addViews();
    }

    private void loadBackgroundImage() {
        background = Utils.createImageIcon("background.jpg");
    }

    private void addViews() {
        add(createKontostandView());
        add(createAuftragseingangView());
        add(createRegalView());
        add(new InfoView());
    }

    private KontostandView createKontostandView() {
        return new KontostandView(bilanz);
    }

    private AuftragseingangView createAuftragseingangView() {
        Auftragseingang model = new Auftragseingang();
        AuftragseingangView view = new AuftragseingangView(model, bilanz);
        AuftragseingangController controller = new AuftragseingangControllerImpl(model, view);
        return view;
    }

    private RegalView createRegalView() {
        return new RegalView(bilanz);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}