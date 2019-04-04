package org.softuni.productshop.domain.models.view;

import java.io.Serializable;

public class ShoppingCartItem implements Serializable {

    private ProductDetailsViewModel product;
    private int quantity;

    public ShoppingCartItem() {
    }

    public ProductDetailsViewModel getProduct() {
        return product;
    }

    public void setProduct(ProductDetailsViewModel product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
