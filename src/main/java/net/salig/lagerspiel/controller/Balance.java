package net.salig.lagerspiel.controller;

import net.salig.lagerspiel.Utils;
import net.salig.lagerspiel.model.Action;
import net.salig.lagerspiel.model.Product;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class Balance {

    private int accountBalance;
    private JLabel label;

    private final BalanceTableModel tableModel = new BalanceTableModel();

    private static final int SCRAP_DEDUCTION = 300;

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public void auftragAbschließen(Product product, Action action) {
        setAccountBalance(switch (action) {
            case Einlagerung, Auslagerung -> accountBalance + product.getPrice();
            case Ablehnen -> accountBalance - product.getPrice();
            case Verschrotten -> accountBalance - SCRAP_DEDUCTION;
            case ERROR -> -1;
        });

        product.setAction(action);
        tableModel.add(product);
    }

    private void updateKontostandLabel() {
        label.setText("<html><font color='white' size='3'>" + Utils.getStringResources().getString("balance")
                + ": " + accountBalance + "€</font></html>");
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
        updateKontostandLabel();
    }

    public AbstractTableModel getTableModel() {
        return tableModel;
    }
}
