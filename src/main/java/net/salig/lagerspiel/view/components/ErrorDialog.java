package net.salig.lagerspiel.view.components;

import net.salig.lagerspiel.Utils;

import javax.swing.*;

public class ErrorDialog extends JOptionPane {

    public static void showErrorDialog(String resourceKey) {
        showMessageDialog(null,
                Utils.getStringResources().getString("error.message." + resourceKey),
                Utils.getStringResources().getString("error.title." + resourceKey), JOptionPane.ERROR_MESSAGE);
    }
}
