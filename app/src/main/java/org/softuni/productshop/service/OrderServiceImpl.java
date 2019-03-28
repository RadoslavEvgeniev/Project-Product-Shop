package org.softuni.productshop.service;

import org.softuni.productshop.domain.entities.Order;
import org.softuni.productshop.domain.entities.Product;
import org.softuni.productshop.domain.entities.User;
import org.softuni.productshop.domain.models.service.UserServiceModel;
import org.softuni.productshop.repository.OrderRepository;
import org.softuni.productshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            ProductRepository productRepository,
            UserService userService
    ) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Override
    public void createOrder(String productId, String name) {
        UserServiceModel userModel = userService.findUserByUserName(name);
        Product product = productRepository.findById(productId)
                .orElseThrow();
        User user = new User();
        user.setId(userModel.getId());
        Order order = new Order();
        order.setProduct(product);
        order.setUser(user);

        orderRepository.save(order);
    }
}
