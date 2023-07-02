/*
 * Copyright (c) 2023 Marko Salig.
 *
 * Licensed under the MIT license: https://opensource.org/licenses/MIT
 * Permission is granted to use, copy, modify, and redistribute the work.
 * Full license information available in the project LICENSE file.
 */

package net.salig.lagerspiel.view.balance;

import net.salig.lagerspiel.Utils;
import net.salig.lagerspiel.controller.Balance;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BalanceWindow extends JFrame {

    private final Balance balance;

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    public BalanceWindow(Balance balance) {
        super(Utils.getString("balance.window.title"));
        this.balance = balance;
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTable table = createBilanzTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JLabel label = new JLabel("   " + Utils.getString("balance.window.overall") + ": " + balance.getAccountBalance() + "€");
        label.setBorder(new LineBorder(Color.BLACK));
        label.setPreferredSize(new Dimension(300, 50));
        add(label, BorderLayout.PAGE_END);

        pack();
        setVisible(true);
    }

    private JTable createBilanzTable() {
        JTable table = new JTable(balance.getTableModel());
        table.setDragEnabled(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setRowSelectionAllowed(false);

        return table;
    }
}