/*
 * Copyright (c) 2023 Marko Salig.
 *
 * Licensed under the MIT license: https://opensource.org/licenses/MIT
 * Permission is granted to use, copy, modify, and redistribute the work.
 * Full license information available in the project LICENSE file.
 */

package net.salig.lagerspiel;

import net.salig.lagerspiel.view.MainView;
import net.salig.lagerspiel.view.components.ErrorDialog;

import javax.swing.*;
import java.awt.*;

public class WarehouseGame extends JFrame {

    private static final int WIDTH = 1440;
    private static final int HEIGHT = 900;

    public WarehouseGame() {
        super(Utils.getString("main.window.title"));

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            ErrorDialog.showErrorDialog("look_and_feel");
        }

        initUI();
    }

    private void initUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        setIconImage(null);

        setContentPane(new MainView());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
