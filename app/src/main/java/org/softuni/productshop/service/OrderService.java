package org.softuni.productshop.service;

import org.softuni.productshop.domain.models.service.OrderServiceModel;

import java.util.BitSet;
import java.util.List;

public interface OrderService {

    void createOrder(OrderServiceModel orderServiceModel);

    List<OrderServiceModel> findAllOrders();

    List<OrderServiceModel> findOrdersByCustomer(String username);

    OrderServiceModel findOrderById(String id);
}
