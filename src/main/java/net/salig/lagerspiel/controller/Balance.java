/*
 * Copyright (c) 2023 Marko Salig.
 *
 * Licensed under the MIT license: https://opensource.org/licenses/MIT
 * Permission is granted to use, copy, modify, and redistribute the work.
 * Full license information available in the project LICENSE file.
 */

package net.salig.lagerspiel.controller;

import net.salig.lagerspiel.Utils;
import net.salig.lagerspiel.model.Action;
import net.salig.lagerspiel.model.Product;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class Balance {

    private int accountBalance = 0;
    private JLabel label;

    private final BalanceTableModel tableModel = new BalanceTableModel();

    private static final int SCRAP_DEDUCTION = 300;

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public void completeOrder(Product product, Action action) {
        setAccountBalance(switch (action) {
            case STORE, RETRIEVE -> accountBalance + product.getPrice();
            case REJECT -> accountBalance - product.getPrice();
            case SCRAP -> accountBalance - SCRAP_DEDUCTION;
            case ERROR -> -1;
        });

        product.setAction(action);
        tableModel.add(product);
    }

    private void updateBalanceLabel() {
        label.setText("<html><font color='white' size='15'>" + Utils.getString("balance")
                + ": <br>" + accountBalance + "€</font></html>");
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
        updateBalanceLabel();
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public AbstractTableModel getTableModel() {
        return tableModel;
    }
}
