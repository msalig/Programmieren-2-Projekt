package net.salig.lagerspiel.controller;

import net.salig.lagerspiel.Utils;
import net.salig.lagerspiel.model.Action;
import net.salig.lagerspiel.model.Product;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class BalanceTableModel extends AbstractTableModel {

    private final ArrayList<Product> transactions = new ArrayList<>();

    private static final String[] COLUMN_NAMES = {
            Utils.getStringResources().getString("balance.window.column_names.revenue"),
            Utils.getStringResources().getString("balance.window.column_names.action"),
            Utils.getStringResources().getString("balance.window.column_names.product")
    };

    @Override
    public int getRowCount() {
        return transactions.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        Product product = transactions.get(rowIndex);
        StringBuilder sb = new StringBuilder();
        if (columnIndex == 0) {
            if (product.getAction() == Action.Verschrotten || product.getAction() == Action.Ablehnen) {
                sb.append(" -");
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

    public void add(Product product) {
        int row = this.getRowCount();
        transactions.add(product);
        fireTableRowsInserted(row, row);
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }
}