package net.salig.lagerspiel.model;

public class AuftragFactory {

    public static Auftrag createAuftrag(String[] order) {
        return new Auftrag(order[1], order[2], order[3], order[4], order[5]);
    }
}
