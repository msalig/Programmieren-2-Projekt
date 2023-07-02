/*
 * Copyright (c) 2023 Marko Salig.
 *
 * Licensed under the MIT license: https://opensource.org/licenses/MIT
 * Permission is granted to use, copy, modify, and redistribute the work.
 * Full license information available in the project LICENSE file.
 */

package net.salig.lagerspiel.draganddrop;

import net.salig.lagerspiel.Utils;
import net.salig.lagerspiel.controller.Balance;
import net.salig.lagerspiel.model.Action;
import net.salig.lagerspiel.model.ProductDTO;
import net.salig.lagerspiel.model.Products;
import net.salig.lagerspiel.view.components.ErrorDialog;
import net.salig.lagerspiel.view.components.StorageArea;
import net.salig.lagerspiel.view.components.ScrapLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;

public class OrderTransferHandler extends TransferHandler {

    private final Balance balance;

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
            if (storageArea.getProdukt() != null) {
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
        if (component instanceof ScrapLabel) {
            if (productDTO.getSource().getName().contains("Auftragseingang")) {
                balance.completeOrder(productDTO.getOrder(), Action.REJECT);
                resetSource(productDTO);
            } else if (productDTO.getSource().getName().contains("Regal")) {
                balance.completeOrder(productDTO.getOrder(), Action.SCRAP);
                resetSource(productDTO);
            }
        } else {
            if (component.getName().contains("Auftragseingang") && productDTO.getSource().getName().contains("Regal")) {
                resetSource(productDTO);
                balance.completeOrder(((StorageArea) component).getProdukt(), Action.RETRIEVE);
                ((StorageArea) component).empty();
            } else if (isValidStorage(productDTO, component) && productDTO.getOrder().getAction() != Action.RETRIEVE) {
                handleOrderImport(productDTO, component);
                resetSource(productDTO);
            }
        }
    }

    private void handleOrderImport(ProductDTO productDTO, Component component) {
        StorageArea storageArea = (StorageArea) component;
        storageArea.setProdukt(productDTO.getSource().getProdukt());
        JLabel label = (JLabel) component;
        label.setIcon(Utils.createImageIcon(productDTO.getOrder().getIconPath(), Utils.IMAGE_SIZE, Utils.IMAGE_SIZE));

        if (!productDTO.getSource().getName().contains("Regal")) {
            balance.completeOrder(productDTO.getOrder(), productDTO.getOrder().getAction());
        }
    }

    private boolean isValidStorage(ProductDTO productDTO, Component component) {
        String componentName = component.getName();
        String produktKind = productDTO.getOrder().getKind();
        String produktSize = productDTO.getOrder().getSize();

        if (component instanceof StorageArea && produktKind.equals(Products.Stein.class.getSimpleName())) {
            if(produktSize.equals(Products.Stein.Size.Schwer.toString()) && componentName.matches("^(?!Regal_1[2-5]$).*")) {
                ErrorDialog.showErrorDialog("heavy_stone");
                return false;
            } else if(produktSize.equals(Products.Stein.Size.Mittel.toString()) && componentName.matches("^Regal_[0-3]$")) {
                ErrorDialog.showErrorDialog("medium_weight_stone");
                return false;
            }
        }

        return true;
    }

    private void resetSource(ProductDTO productDTO) {
        productDTO.getSource().empty();
    }
}