/*
 * Copyright (c) 2023 Marko Salig.
 *
 * Licensed under the MIT license: https://opensource.org/licenses/MIT
 * Permission is granted to use, copy, modify, and redistribute the work.
 * Full license information available in the project LICENSE file.
 */

package net.salig.lagerspiel.model;

import net.salig.lagerspiel.view.components.StorageArea;

public class ProductDTO {
    private final StorageArea source;
    private final Product product;

    public ProductDTO(StorageArea source, Product product) {
        this.source = source;
        this.product = product;
    }

    public StorageArea getSource() {
        return source;
    }

    public Product getOrder() {
        return product;
    }
}