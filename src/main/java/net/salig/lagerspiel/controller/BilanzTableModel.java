package net.salig.lagerspiel.controller;

import net.salig.lagerspiel.model.Action;
import net.salig.lagerspiel.model.Produkt;

import javax.swing.table.AbstractTableModel;

public class BilanzTableModel extends AbstractTableModel {

    private final Bilanz bilanz;

    private final String[] columnNames = {"Umsätze", "Aktion", "Produkt"};

    public BilanzTableModel(Bilanz bilanz) {
        this.bilanz = bilanz;
    }

    @Override
    public int getRowCount() {
        return bilanz.getTransaktionen().size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Produkt produkt = bilanz.getTransaktionen().get(rowIndex);
        StringBuilder sb = new StringBuilder();
        switch (columnIndex) {
            case 0 -> {
                if (produkt.getAction() == Action.Verschrotten) {
                    sb.append("-");
                } else {
                    sb.append("+");
                }
                sb.append(produkt.getPrice()).append("€");
            }
            case 1 -> sb.append(produkt.getAction());
            case 2 -> sb.append(produkt.getKind()).append(", ").append(produkt.getType()).append(", ").append(produkt.getSize());
        }

        return sb.toString();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
