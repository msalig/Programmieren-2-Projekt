/*
 * Copyright (c) 2023 Marko Salig.
 *
 * Licensed under the MIT license: https://opensource.org/licenses/MIT
 * Permission is granted to use, copy, modify, and redistribute the work.
 * Full license information available in the project LICENSE file.
 */

package net.salig.lagerspiel.view.components;

import net.salig.lagerspiel.Utils;

import javax.swing.*;

public class ErrorDialog extends JOptionPane {

    public static void showErrorDialog(String resourceKey) {
        showMessageDialog(null,
                Utils.getString("error.message." + resourceKey),
                Utils.getString("error.title." + resourceKey), JOptionPane.ERROR_MESSAGE);
    }
}
