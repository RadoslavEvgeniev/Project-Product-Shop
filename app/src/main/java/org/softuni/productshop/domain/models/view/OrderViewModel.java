package org.softuni.productshop.domain.models.view;

import org.modelmapper.ModelMapper;
import org.softuni.productshop.domain.models.service.ProductServiceModel;
import org.softuni.productshop.domain.models.service.UserServiceModel;
import org.softuni.productshop.mappings.IHaveCustomMappings;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderViewModel {

    private String id;
    private List<OrderProductViewModel> products;
    private UserProfileViewModel customer;
    private BigDecimal totalPrice;
    private LocalDateTime finishedOn;

    public OrderViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<OrderProductViewModel> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProductViewModel> products) {
        this.products = products;
    }

    public UserProfileViewModel getCustomer() {
        return customer;
    }

    public void setCustomer(UserProfileViewModel customer) {
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
