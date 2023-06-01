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

    public Product getAuftrag() {
        return product;
    }
}