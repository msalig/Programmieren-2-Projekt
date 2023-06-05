package net.salig.lagerspiel.draganddrop;

import net.salig.lagerspiel.model.ProductDTO;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

public class OrderTransferable implements Transferable {
    public static final DataFlavor DATA_FLAVOR = new DataFlavor(ProductDTO.class, "java/Product");
    private final ProductDTO produkt;

    public OrderTransferable(ProductDTO produkt) {
        this.produkt = produkt;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{DATA_FLAVOR};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(DATA_FLAVOR);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) {
        return produkt;
    }
}
