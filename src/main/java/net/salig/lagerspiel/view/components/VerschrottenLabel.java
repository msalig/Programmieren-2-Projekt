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
        setName(this.getClass().getSimpleName());
        setTransferHandler(new AuftragTransferHandler(bilanz));
        addMouseListener(new VerschrottenLabelMouseAdapter());
    }

    private class VerschrottenLabelMouseAdapter extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e);
            setIcon(Utils.createImageIcon("empty-trashcan.png", 100, 100));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseExited(e);
            setIcon(Utils.createImageIcon("full-trashcan.png", 100, 100));
        }
    }
}
