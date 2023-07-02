/*
 * Copyright (c) 2023 Marko Salig.
 *
 * Licensed under the MIT license: https://opensource.org/licenses/MIT
 * Permission is granted to use, copy, modify, and redistribute the work.
 * Full license information available in the project LICENSE file.
 */

package net.salig.lagerspiel;

import javax.swing.*;

public class Start {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WarehouseGame::new);
    }
}
