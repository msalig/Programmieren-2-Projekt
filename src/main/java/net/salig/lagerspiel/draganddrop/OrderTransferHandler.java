package net.salig.lagerspiel.draganddrop;

import net.salig.lagerspiel.Utils;
import net.salig.lagerspiel.controller.Balance;
import net.salig.lagerspiel.model.Action;
import net.salig.lagerspiel.model.ProductDTO;
import net.salig.lagerspiel.model.Products;
import net.salig.lagerspiel.view.components.StorageArea;
import net.salig.lagerspiel.view.components.ScrapLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;

public class OrderTransferHandler extends TransferHandler {

    private Balance balance;

    public OrderTransferHandler(Balance balance) {
        this.balance = balance;
    }

    @Override
    public boolean canImport(TransferSupport support) {
        Component component = support.getComponent();
        return component instanceof JLabel
                && support.isDataFlavorSupported(OrderTransferable.DATA_FLAVOR)
                && (component.getName().equals(ScrapLabel.class.getSimpleName())
                || (component.getName().contains("Regal") && ((JLabel) component).getIcon() == null)
                || (component.getName().contains("Auftragseingang")
                && ((StorageArea) component).getLabel().getIcon() != null));
    }

    @Override
    public boolean importData(TransferSupport support) {
        try {
            Transferable t = support.getTransferable();
            Object value = t.getTransferData(OrderTransferable.DATA_FLAVOR);
            if (value instanceof ProductDTO) {
                Component component = support.getComponent();
                if (component instanceof JLabel) {
                    handleImportData((ProductDTO) value, component);
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
        if (c instanceof StorageArea) {
            StorageArea storageArea = (StorageArea) c;
            if (storageArea.getProdukt() != null && storageArea.getProdukt().getAction() != Action.Auslagerung) {
                return new OrderTransferable(new ProductDTO(storageArea, storageArea.getProdukt()));
            }
        }
        return null;
    }

    @Override
    public int getSourceActions(JComponent c) {
        return DnDConstants.ACTION_COPY_OR_MOVE;
    }

    private void handleImportData(ProductDTO productDTO, Component component) {
        if (!(component instanceof ScrapLabel)) {
            if (component.getName().contains("Auftragseingang") && productDTO.getSource().getName().contains("Regal")) {
                resetSource(productDTO);
                ((StorageArea) component).emptyStellplatz();
                balance.auftragAbschließen(((StorageArea) component).getProdukt(), Action.Auslagerung);
            } else if (!isInvalidStorage(productDTO, component)) {
                handleLagerplatzImport(productDTO, component);
                resetSource(productDTO);
            }
        } else if (productDTO.getSource().getName().contains("Auftragseingang")) {
            balance.auftragAbschließen(productDTO.getAuftrag(), Action.Ablehnen);
            resetSource(productDTO);
        } else if (productDTO.getSource().getName().contains("Regal")) {
            balance.auftragAbschließen(productDTO.getAuftrag(), Action.Verschrotten);
            resetSource(productDTO);
        }
    }

    private void handleLagerplatzImport(ProductDTO productDTO, Component component) {
        StorageArea storageArea = (StorageArea) component;
        storageArea.setProdukt(productDTO.getSource().getProdukt());
        JLabel label = (JLabel) component;
        label.setIcon(Utils.createImageIcon(productDTO.getAuftrag().getIconPath(), Utils.IMAGE_SIZE, Utils.IMAGE_SIZE));
        if (!productDTO.getSource().getName().contains("Regal")) {
            balance.auftragAbschließen(productDTO.getAuftrag(), productDTO.getAuftrag().getAction());
        }
    }

    private boolean isInvalidStorage(ProductDTO productDTO, Component component) {
        String componentName = component.getName();
        String produktKind = productDTO.getAuftrag().getKind();
        String produktSize = productDTO.getAuftrag().getSize();

        if (!componentName.equals(ScrapLabel.class.getSimpleName())
                && !componentName.equals("Regal_12")
                && !componentName.equals("Regal_13")
                && !componentName.equals("Regal_14")
                && !componentName.equals("Regal_15")
                && produktKind.equals(Products.Stein.toString())
                && produktSize.equals("Schwer")) {
            JOptionPane.showMessageDialog(null,
                    Utils.getStringResources().getString("error_message_heavy_stone"),
                    Utils.getStringResources().getString("error_title_heavy_stone"),
                    JOptionPane.ERROR_MESSAGE);
            return true;
        }

        if (produktKind.equals(Products.Stein.toString())
                && produktSize.equals("Mittel")
                && (componentName.equals("Regal_0")
                || componentName.equals("Regal_1")
                || componentName.equals("Regal_2")
                || componentName.equals("Regal_3"))) {
            JOptionPane.showMessageDialog(null,
                    Utils.getStringResources().getString("error_message_medium_weight_stone"),
                    Utils.getStringResources().getString("error_title_medium_weight_stone"), JOptionPane.ERROR_MESSAGE);
            return true;
        }

        return false;
    }

    private void resetSource(ProductDTO productDTO) {
        productDTO.getSource().emptyStellplatz();
    }
}