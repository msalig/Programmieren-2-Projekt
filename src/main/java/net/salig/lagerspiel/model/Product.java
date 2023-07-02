/*
 * Copyright (c) 2023 Marko Salig.
 *
 * Licensed under the MIT license: https://opensource.org/licenses/MIT
 * Permission is granted to use, copy, modify, and redistribute the work.
 * Full license information available in the project LICENSE file.
 */

package net.salig.lagerspiel.model;

public class Product {

    private Action action;

    private final String kind;
    private final String type;
    private final String size;
    private final int price;

    public Product(String[] order) {
        this.action = Action.parseString(order[1]);
        this.kind = order[2];
        this.type = order[3];
        this.size = order[4];
        this.price = Integer.parseInt(order[5]);
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Action getAction() {
        return action;
    }

    public String getKind() {
        return kind;
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    public int getPrice() {
        return price;
    }

    public String getIconPath() {
        return "assets/" + kind + "/" + type + "_" + size + ".png";
    }
}
