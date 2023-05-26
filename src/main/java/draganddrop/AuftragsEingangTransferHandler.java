package main.java.draganddrop;

import main.java.model.AuftragDTO;
import main.java.model.AuftragFactory;
import main.java.view.AuftragsEingangView;

import javax.swing.*;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;

public class AuftragsEingangTransferHandler extends TransferHandler {

    @Override
    protected Transferable createTransferable(JComponent c) {
        Transferable t = null;
        if (c instanceof AuftragsEingangView.Label) {
            AuftragsEingangView.Label label = (AuftragsEingangView.Label) c;
            t = new AuftragTransferable(new AuftragDTO(label, AuftragFactory.createAuftrag(label.getInformation())));
        }
        return t;
    }

    @Override
    public int getSourceActions(JComponent c) {
        return DnDConstants.ACTION_COPY_OR_MOVE;
    }
}
