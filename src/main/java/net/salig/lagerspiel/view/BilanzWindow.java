package net.salig.lagerspiel.view;

import net.salig.lagerspiel.controller.Bilanz;
import net.salig.lagerspiel.controller.BilanzTableModel;

import javax.swing.*;

public class BilanzWindow extends JFrame {

    private Bilanz bilanz;

    public BilanzWindow(Bilanz bilanz) {
        this.bilanz = bilanz;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLayout(null);
        setLocationRelativeTo(null);

        setVisible(true);

        addViews();
    }

    private void addViews() {
        JPanel panel = new JPanel();
        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);

        table.setDragEnabled(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setColumnSelectionAllowed(false);
        table.setCellSelectionEnabled(false);
        table.setRowSelectionAllowed(false);
        table.setModel(new BilanzTableModel(bilanz));
        table.setSize(this.getWidth(), 400);

        panel.add(scrollPane);
        panel.setBounds(0, 0, this.getWidth(), 400);
        this.add(panel);
    }
}
