package net.salig.lagerspiel.view;

import net.salig.lagerspiel.controller.Bilanz;
import net.salig.lagerspiel.controller.BilanzTableModel;

import javax.swing.*;
import java.awt.*;

public class BilanzWindow extends JFrame {

    private final Bilanz bilanz;

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    public BilanzWindow(Bilanz bilanz) {
        this.bilanz = bilanz;
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
        JTable table = new JTable(new BilanzTableModel(bilanz));
        table.setDragEnabled(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setRowSelectionAllowed(false);

        return table;
    }
}