package net.salig.lagerspiel.view.components;

import net.salig.lagerspiel.Utils;
import net.salig.lagerspiel.controller.Bilanz;
import net.salig.lagerspiel.draganddrop.AuftragTransferHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VerschrottenLabel extends JLabel {

    public VerschrottenLabel(Bilanz bilanz) {
        super(Utils.createImageIcon("full-trashcan.png"), SwingConstants.CENTER);
        setToolTipText("Auftrag ablehnen / verschrotten");
        setPreferredSize(new Dimension(400, 400));
        setName(getClass().getSimpleName());
        setTransferHandler(new AuftragTransferHandler(bilanz));
        addMouseListener(new VerschrottenLabelMouseAdapter());
    }

    private static class VerschrottenLabelMouseAdapter extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e);
            ((VerschrottenLabel) e.getSource()).setIcon(Utils.createImageIcon("empty-trashcan.png"));        }

        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseExited(e);
            ((VerschrottenLabel) e.getSource()).setIcon(Utils.createImageIcon("full-trashcan.png"));        }
    }
}
