package net.salig.lagerspiel.draganddrop;

import net.salig.lagerspiel.Utils;
import net.salig.lagerspiel.controller.Bilanz;
import net.salig.lagerspiel.model.Auftrag;
import net.salig.lagerspiel.model.AuftragDTO;

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
                    if(!Objects.equals(component.getName(), "12") && !Objects.equals(component.getName(), "13") && !Objects.equals(component.getName(), "14")
                            && !Objects.equals(component.getName(), "15") && ((AuftragDTO) value).getAuftrag().getKind().equals("Stein")) {
                        JOptionPane.showMessageDialog(null, "Stein kann nur in der unteren Reihe gelagert werden!", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Auftrag auftrag = ((AuftragDTO) value).getAuftrag();
                        ((JLabel) component).setIcon(Utils.createImageIcon(auftrag.getIconPath(), 150, 150));
                        bilanz.setBilanz(Integer.parseInt(auftrag.getPrice()));

                        ((AuftragDTO) value).getSource().setIcon(null);
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