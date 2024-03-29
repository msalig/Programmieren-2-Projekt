/*
 * Copyright (c) 2023 Marko Salig.
 *
 * Licensed under the MIT license: https://opensource.org/licenses/MIT
 * Permission is granted to use, copy, modify, and redistribute the work.
 * Full license information available in the project LICENSE file.
 */

package net.salig.lagerspiel.view.components;

import net.salig.lagerspiel.Utils;
import net.salig.lagerspiel.controller.Balance;
import net.salig.lagerspiel.draganddrop.OrderTransferHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ScrapLabel extends JLabel {

    public ScrapLabel(Balance balance) {
        super(Utils.createImageIcon("assets/full-trashcan.png"), SwingConstants.CENTER);
        setToolTipText(Utils.getString("tooltip.scrap_or_reject_order"));
        setPreferredSize(new Dimension(400, 400));
        setName(getClass().getSimpleName());
        setTransferHandler(new OrderTransferHandler(balance));
        addMouseListener(new VerschrottenLabelMouseAdapter());
    }

    private static class VerschrottenLabelMouseAdapter extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e);
            ((ScrapLabel) e.getSource()).setIcon(Utils.createImageIcon("assets/empty-trashcan.png"));        }

        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseExited(e);
            ((ScrapLabel) e.getSource()).setIcon(Utils.createImageIcon("assets/full-trashcan.png"));        }
    }
}
