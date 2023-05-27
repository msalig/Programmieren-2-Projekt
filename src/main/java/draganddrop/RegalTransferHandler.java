package main.java.draganddrop;

import main.java.controller.Bilanz;
import main.java.model.Auftrag;
import main.java.model.AuftragDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.util.Objects;

public class RegalTransferHandler extends TransferHandler {

    Bilanz bilanz;

    public RegalTransferHandler(Bilanz bilanz) {
        this.bilanz = bilanz;
    }

    @Override
    public boolean canImport(TransferSupport support) {
        if(support.getComponent() instanceof JLabel && support.isDataFlavorSupported(AuftragTransferable.DATA_FLAVOR)) {
            JLabel lagerplatz = (JLabel) support.getComponent();
            return lagerplatz.getText().isEmpty();
        }
        return false;
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
                    if(Objects.equals(component.getName(), "12") && ((AuftragDTO) value).getAuftrag().getType().equals("Stone")) {
                        JOptionPane.showMessageDialog(null, "Not possible", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Auftrag auftrag = ((AuftragDTO) value).getAuftrag();
                        ((JLabel) component).setText(auftrag.getType());
                        bilanz.setBilanz(Integer.parseInt(auftrag.getPrice()));

                        ((AuftragDTO) value).getSource().setText("");
                        ((AuftragDTO) value).getSource().setInformation(null);
                        accept = true;
                    }
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
