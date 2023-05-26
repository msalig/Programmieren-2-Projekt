package main.java.draganddrop;

import main.java.model.AuftragDTO;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class AuftragTransferable implements Transferable {
    public static final DataFlavor LIST_ITEM_DATA_FLAVOR = new DataFlavor(AuftragDTO.class, "java/Produkt");
    private AuftragDTO produkt;

    public AuftragTransferable(AuftragDTO produkt) {
        this.produkt = produkt;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{LIST_ITEM_DATA_FLAVOR};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(LIST_ITEM_DATA_FLAVOR);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        return produkt;
    }
}
