package net.salig.lagerspiel.draganddrop;

import net.salig.lagerspiel.Utils;
import net.salig.lagerspiel.controller.Bilanz;
import net.salig.lagerspiel.model.Action;
import net.salig.lagerspiel.model.ProduktDTO;
import net.salig.lagerspiel.view.Lagerplatz;
import net.salig.lagerspiel.view.components.VerschrottenLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;

public class AuftragTransferHandler extends TransferHandler {

    private Bilanz bilanz;

    public AuftragTransferHandler(Bilanz bilanz) {
        this.bilanz = bilanz;
    }

    @Override
    public boolean canImport(TransferSupport support) {
        Component component = support.getComponent();
        return component instanceof JLabel
                && support.isDataFlavorSupported(AuftragTransferable.DATA_FLAVOR)
                && (component.getName().equals(VerschrottenLabel.class.getSimpleName())
                || component.getName().contains("Regal"));
    }

    @Override
    public boolean importData(TransferSupport support) {
        try {
            Transferable t = support.getTransferable();
            Object value = t.getTransferData(AuftragTransferable.DATA_FLAVOR);
            if (value instanceof ProduktDTO) {
                Component component = support.getComponent();
                if (component instanceof JLabel) {
                    handleImportData((ProduktDTO) value, component);
                    return true;
                }
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return false;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        if (c instanceof Lagerplatz) {
            Lagerplatz lagerplatz = (Lagerplatz) c;
            if (lagerplatz.getProdukt() != null) {
                return new AuftragTransferable(new ProduktDTO(lagerplatz, lagerplatz.getProdukt()));
            }
        }
        return null;
    }

    @Override
    public int getSourceActions(JComponent c) {
        return DnDConstants.ACTION_COPY_OR_MOVE;
    }

    private void handleImportData(ProduktDTO produktDTO, Component component) {
        if (!(component instanceof VerschrottenLabel) && !isInvalidStorage(produktDTO, component)) {
            handleLagerplatzImport(produktDTO, component);
        } else if (produktDTO.getSource().getName().contains("Auftragseingang")) {
            bilanz.auftragAbschließen(produktDTO.getAuftrag(), Action.Ablehnen);
        } else {
            bilanz.auftragAbschließen(produktDTO.getAuftrag(), Action.Verschrotten);
        }
        resetSource(produktDTO);
    }

    private void handleLagerplatzImport(ProduktDTO produktDTO, Component component) {
        Lagerplatz lagerplatz = (Lagerplatz) component;
        lagerplatz.setProdukt(produktDTO.getSource().getProdukt());
        JLabel label = (JLabel) component;
        label.setIcon(Utils.createImageIcon(produktDTO.getAuftrag().getIconPath(), Utils.IMAGE_SIZE, Utils.IMAGE_SIZE));
        if (!produktDTO.getSource().getName().contains("Regal")) {
            bilanz.auftragAbschließen(produktDTO.getAuftrag(), produktDTO.getAuftrag().getAction());
        }
    }

    private boolean isInvalidStorage(ProduktDTO produktDTO, Component component) {
        String componentName = component.getName();
        String produktKind = produktDTO.getAuftrag().getKind();
        String produktSize = produktDTO.getAuftrag().getSize();

        if (!componentName.equals(VerschrottenLabel.class.getSimpleName())
                && !componentName.equals("Regal_12")
                && !componentName.equals("Regal_13")
                && !componentName.equals("Regal_14")
                && !componentName.equals("Regal_15")
                && produktKind.equals("Stein")
                && produktSize.equals("Schwer")) {
            JOptionPane.showMessageDialog(null, "Schwere Steine können nur in der unteren Reihe gelagert werden!", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (produktKind.equals("Stein")
                && produktSize.equals("Mittel")
                && (componentName.equals("Regal_0")
                || componentName.equals("Regal_1")
                || componentName.equals("Regal_2")
                || componentName.equals("Regal_3"))) {
            JOptionPane.showMessageDialog(null, "Mittelschwere Steine können nicht in der obersten Reihe gelagert werden!", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        return false;
    }

    private void resetSource(ProduktDTO produktDTO) {
        produktDTO.getSource().emptyStellplatz();
    }
}