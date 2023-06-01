package net.salig.lagerspiel.controller;

import net.salig.lagerspiel.Utils;
import net.salig.lagerspiel.model.Action;
import net.salig.lagerspiel.model.Product;

import javax.swing.table.AbstractTableModel;

public class BalanceTableModel extends AbstractTableModel {

    private final Balance balance;

    private static final String[] COLUMN_NAMES = Utils.getStringResources().getStringArray("balance_window_column_names");

    public BalanceTableModel(Balance balance) {
        this.balance = balance;
    }

    @Override
    public int getRowCount() {
        return balance.getTransactions().size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        Product product = balance.getTransactions().get(rowIndex);
        StringBuilder sb = new StringBuilder();
        if (columnIndex == 0) {
            if (product.getAction() == Action.Verschrotten) {
                sb.append("-");
            } else {
                sb.append("+");
            }
            sb.append(product.getPrice()).append("€");
        } else if (columnIndex == 1) {
            sb.append(product.getAction());
        } else if (columnIndex == 2) {
            sb.append(product.getKind()).append(", ").append(product.getType()).append(", ").append(product.getSize());
        }

        return sb.toString();
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }
}