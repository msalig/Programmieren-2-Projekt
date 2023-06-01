package net.salig.lagerspiel.view;

import net.salig.lagerspiel.Utils;
import net.salig.lagerspiel.controller.Balance;
import net.salig.lagerspiel.controller.BalanceTableModel;

import javax.swing.*;
import java.awt.*;

public class BalanceWindow extends JFrame {

    private final Balance balance;

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    public BalanceWindow(Balance balance) {
        super(Utils.getStringResources().getString("balance_window_title"));
        this.balance = balance;
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTable table = createBilanzTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private JTable createBilanzTable() {
        JTable table = new JTable(new BalanceTableModel(balance));
        table.setDragEnabled(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setRowSelectionAllowed(false);

        return table;
    }
}