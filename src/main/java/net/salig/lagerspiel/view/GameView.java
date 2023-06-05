package net.salig.lagerspiel.view;

import net.salig.lagerspiel.Utils;
import net.salig.lagerspiel.controller.Balance;
import net.salig.lagerspiel.controller_real.OrderIntakeController;
import net.salig.lagerspiel.controller_real.OrderIntakeControllerImpl;
import net.salig.lagerspiel.model_real.OrderIntake;
import net.salig.lagerspiel.view.balance.BalanceView;
import net.salig.lagerspiel.view.info.InfoView;
import net.salig.lagerspiel.view.regal.RegalView;
import net.salig.lagerspiel.view_real.OrderIntakeView;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {

    private final Balance balance;
    private ImageIcon background;

    private static final int WIDTH = 1440;
    private static final int HEIGHT = 900;

    public GameView() {
        balance = new Balance();
        setLayout(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        loadBackgroundImage();
        addViews();
    }

    private void loadBackgroundImage() {
        background = Utils.createImageIcon("assets/background.jpg");
    }

    private void addViews() {
        add(createKontostandView());
        add(createAuftragseingangView());
        add(createRegalView());
        add(new InfoView());
    }

    private BalanceView createKontostandView() {
        return new BalanceView(balance);
    }

    private OrderIntakeView createAuftragseingangView() {
        OrderIntake model = new OrderIntake();
        OrderIntakeView view = new OrderIntakeView(model, balance);
        OrderIntakeController controller = new OrderIntakeControllerImpl(model, view);
        return view;
    }

    private RegalView createRegalView() {
        return new RegalView(balance);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}