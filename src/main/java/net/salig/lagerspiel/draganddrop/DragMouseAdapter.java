package net.salig.lagerspiel.draganddrop;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DragMouseAdapter extends MouseAdapter {

    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        JLabel label = (JLabel) e.getSource();
        var handler = label.getTransferHandler();
        handler.exportAsDrag(label, e, TransferHandler.COPY);
    }
}
