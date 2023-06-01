package net.salig.lagerspiel.model;

public class Produkt {

    private final String[] order;

    private Action action;

    public Produkt(String[] order) {
        this.order = order;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Action getAction() {
        if (this.action == null) {
            return Action.parseString(order[1]);
        } else {
            return this.action;
        }
    }

    public String getKind() {
        return order[2];
    }

    public String getType() {
        return order[3];
    }

    public String getSize() {
        return order[4];
    }

    public int getPrice() {
        return Integer.parseInt(order[5]);
    }

    public String getIconPath() {
        return order[2] + "/" + order[3] + "_" + order[4] + ".png";
    }
}
