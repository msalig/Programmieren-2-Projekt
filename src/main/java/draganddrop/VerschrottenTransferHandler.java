package main.java.draganddrop;

import main.java.model.AuftragDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;

public class VerschrottenTransferHandler extends TransferHandler {

    @Override
    public boolean canImport(TransferSupport support) {
        return (support.getComponent() instanceof JLabel) && support.isDataFlavorSupported(AuftragTransferable.DATA_FLAVOR);
    }

    @Override
    public boolean importData(TransferSupport support) {
        boolean accept = false;
        try {
            Transferable t = support.getTransferable();
            Object value = t.getTransferData(AuftragTransferable.DATA_FLAVOR);
            if (value instanceof AuftragDTO) {
                Component component = support.getComponent();
                if (component instanceof JLabel) {
                    ((AuftragDTO) value).getSource().setIcon(null);
                    ((AuftragDTO) value).getSource().setInformation(null);
                    accept = true;
                }
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return accept;
    }

    @Override
    public int getSourceActions(JComponent c) {
        return DnDConstants.ACTION_COPY_OR_MOVE;
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        System.out.println("ExportDone");
        // Here you need to decide how to handle the completion of the transfer,
        // should you remove the item from the list or not...
    }
}
