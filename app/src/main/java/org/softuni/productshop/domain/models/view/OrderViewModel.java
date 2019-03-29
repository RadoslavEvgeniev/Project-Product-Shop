package org.softuni.productshop.domain.models.view;

import org.modelmapper.ModelMapper;
import org.softuni.productshop.mappings.IHaveCustomMappings;

import java.math.BigDecimal;

public class OrderViewModel {
    private String imageUrl;
    private String name;
    private BigDecimal price;
    private String customer;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
