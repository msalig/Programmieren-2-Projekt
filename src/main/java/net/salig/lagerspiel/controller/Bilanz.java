package net.salig.lagerspiel.controller;

import net.salig.lagerspiel.model.Action;
import net.salig.lagerspiel.model.Produkt;

import javax.swing.*;
import java.util.ArrayList;

public class Bilanz {

    private int accountBalance;
    private final ArrayList<Produkt> transactions = new ArrayList<>();
    private JLabel label;

    private static final int VERSCHROTTEN_DEDUCTION = 300;

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public void auftragAbschließen(Produkt produkt, Action action) {
        setAccountBalance(switch (action) {
            case Einlagerung, Auslagerung -> accountBalance + produkt.getPrice();
            case Ablehnen -> accountBalance - produkt.getPrice();
            case Verschrotten -> accountBalance - VERSCHROTTEN_DEDUCTION;
            case ERROR -> -1;
        });

        produkt.setAction(action);
        transactions.add(produkt);
    }

    private void updateKontostandLabel() {
        label.setText("<html><font color='white' size='3'>Balance: " + accountBalance + "€</font></html>");
    }

    public ArrayList<Produkt> getTransactions() {
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
