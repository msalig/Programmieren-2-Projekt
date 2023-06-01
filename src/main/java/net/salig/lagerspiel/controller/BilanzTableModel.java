package net.salig.lagerspiel.controller;

import net.salig.lagerspiel.model.Action;
import net.salig.lagerspiel.model.Produkt;

import javax.swing.table.AbstractTableModel;

public class BilanzTableModel extends AbstractTableModel {

    private final Bilanz bilanz;

    private static final String[] COLUMN_NAMES = {"Umsätze", "Aktion", "Produkt"};

    public BilanzTableModel(Bilanz bilanz) {
        this.bilanz = bilanz;
    }

    @Override
    public int getRowCount() {
        return bilanz.getTransactions().size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        Produkt produkt = bilanz.getTransactions().get(rowIndex);
        StringBuilder sb = new StringBuilder();
        if (columnIndex == 0) {
            if (produkt.getAction() == Action.Verschrotten) {
                sb.append("-");
            } else {
                sb.append("+");
            }
            sb.append(produkt.getPrice()).append("€");
        } else if (columnIndex == 1) {
            sb.append(produkt.getAction());
        } else if (columnIndex == 2) {
            sb.append(produkt.getKind()).append(", ").append(produkt.getType()).append(", ").append(produkt.getSize());
        }

        return sb.toString();
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }
}