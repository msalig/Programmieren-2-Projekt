package main.java.draganddrop;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DragMouseAdapter extends MouseAdapter {

    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        var c = (JComponent) e.getSource();
        JLabel label = (JLabel) c;
        var handler = label.getTransferHandler();
        handler.exportAsDrag(label, e, TransferHandler.COPY);
    }
}
