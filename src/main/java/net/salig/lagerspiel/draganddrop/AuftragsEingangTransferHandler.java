package net.salig.lagerspiel.draganddrop;

import net.salig.lagerspiel.model.AuftragDTO;
import net.salig.lagerspiel.model.AuftragFactory;
import net.salig.lagerspiel.view.AuftragsEingangView;

import javax.swing.*;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;

public class AuftragsEingangTransferHandler extends TransferHandler {

    @Override
    protected Transferable createTransferable(JComponent c) {
        Transferable t = null;
        if (c instanceof AuftragsEingangView.Label) {
            AuftragsEingangView.Label label = (AuftragsEingangView.Label) c;
            if(label.getInformation() != null)
                t = new AuftragTransferable(new AuftragDTO(label, AuftragFactory.createAuftrag(label.getInformation())));
        }
        return t;
    }

    @Override
    public int getSourceActions(JComponent c) {
        return DnDConstants.ACTION_COPY_OR_MOVE;
    }
}
