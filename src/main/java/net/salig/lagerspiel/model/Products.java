package net.salig.lagerspiel.model;

public enum Products {
    None;

    public enum Holz {
        Kiefer, Buche, Eiche;

        public enum Size {
            Bretter, Balken, Scheit;
        }
    }

    public enum Papier {
        Blau, Weis, Gruen;

        public enum Size {
            A5, A4, A3;
        }
    };

    public enum Stein {
        Sandstein, Marmor, Granit;

        public enum Size {
            Leicht, Mittel, Schwer;
        }
    }
}