package net.salig.lagerspiel.model;

public enum Action {
    Einlagerung, Auslagerung, Ablehnen, Verschrotten, ERROR;

    public static Action parseString(String action) {
        return switch (action.toLowerCase()) {
            case "einlagerung" -> Einlagerung;
            case "auslagerung" -> Auslagerung;
            case "ablehnen" -> Ablehnen;
            case "verschrotten" -> Verschrotten;
            default -> ERROR;
        };
    }
}
