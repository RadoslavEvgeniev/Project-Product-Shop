package org.softuni.productshop.domain.models.service;

import org.modelmapper.ModelMapper;
import org.softuni.productshop.domain.entities.Order;
import org.softuni.productshop.domain.entities.Product;
import org.softuni.productshop.domain.entities.User;
import org.softuni.productshop.mappings.IHaveCustomMappings;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderServiceModel extends BaseServiceModel {

    private List<OrderProductServiceModel> products;
    private UserServiceModel customer;
    private BigDecimal totalPrice;
    private LocalDateTime finishedOn;

    public OrderServiceModel() {
    }

    public List<OrderProductServiceModel> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProductServiceModel> products) {
        this.products = products;
    }

    public UserServiceModel getCustomer() {
        return customer;
    }

    public void setCustomer(UserServiceModel customer) {
        this.customer = customer;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getFinishedOn() {
        return finishedOn;
    }

    public void setFinishedOn(LocalDateTime finishedOn) {
        this.finishedOn = finishedOn;
    }
}
