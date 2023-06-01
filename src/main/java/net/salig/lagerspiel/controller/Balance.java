package net.salig.lagerspiel.controller;

import net.salig.lagerspiel.Utils;
import net.salig.lagerspiel.model.Action;
import net.salig.lagerspiel.model.Product;

import javax.swing.*;
import java.util.ArrayList;

public class Balance {

    private int accountBalance;
    private final ArrayList<Product> transactions = new ArrayList<>();
    private JLabel label;

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
        transactions.add(product);
    }

    private void updateKontostandLabel() {
        label.setText("<html><font color='white' size='3'>" + Utils.getStringResources().getString("balance")
                + ": " + accountBalance + "€</font></html>");
    }

    public ArrayList<Product> getTransactions() {
        return transactions;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
        updateKontostandLabel();
    }
}
