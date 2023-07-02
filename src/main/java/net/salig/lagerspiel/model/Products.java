/*
 * Copyright (c) 2023 Marko Salig.
 *
 * Licensed under the MIT license: https://opensource.org/licenses/MIT
 * Permission is granted to use, copy, modify, and redistribute the work.
 * Full license information available in the project LICENSE file.
 */

package net.salig.lagerspiel.model;

public enum Products {
    None;

    public enum Stein {
        Sandstein, Marmor, Granit;

        public enum Size {
            Leicht, Mittel, Schwer;
        }
    }
}