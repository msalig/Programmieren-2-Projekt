package net.salig.lagerspiel.draganddrop;

import net.salig.lagerspiel.model.ProduktDTO;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

public class AuftragTransferable implements Transferable {
    public static final DataFlavor DATA_FLAVOR = new DataFlavor(ProduktDTO.class, "java/Produkt");
    private final ProduktDTO produkt;

    public AuftragTransferable(ProduktDTO produkt) {
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
