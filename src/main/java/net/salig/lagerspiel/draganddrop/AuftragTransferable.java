package net.salig.lagerspiel.draganddrop;

import net.salig.lagerspiel.model.AuftragDTO;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

public class AuftragTransferable implements Transferable {
    public static final DataFlavor DATA_FLAVOR = new DataFlavor(AuftragDTO.class, "java/Produkt");
    private final AuftragDTO produkt;

    public AuftragTransferable(AuftragDTO produkt) {
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
