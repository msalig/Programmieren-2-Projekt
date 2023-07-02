/*
 * Copyright (c) 2023 Marko Salig.
 *
 * Licensed under the MIT license: https://opensource.org/licenses/MIT
 * Permission is granted to use, copy, modify, and redistribute the work.
 * Full license information available in the project LICENSE file.
 */

package net.salig.lagerspiel.model;

public enum Action {
    STORE, RETRIEVE, REJECT, SCRAP, ERROR;

    public static Action parseString(String action) {
        return switch (action.toLowerCase()) {
            case "einlagerung" -> STORE;
            case "auslagerung" -> RETRIEVE;
            case "ablehnen" -> REJECT;
            case "verschrotten" -> SCRAP;
            default -> ERROR;
        };
    }
}
