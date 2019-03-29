package org.softuni.productshop.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.ValidationException;
import org.softuni.productshop.domain.entities.Order;
import org.softuni.productshop.domain.entities.Product;
import org.softuni.productshop.domain.entities.User;
import org.softuni.productshop.domain.models.service.OrderServiceModel;
import org.softuni.productshop.domain.models.service.UserServiceModel;
import org.softuni.productshop.repository.OrderRepository;
import org.softuni.productshop.repository.ProductRepository;
import org.softuni.productshop.validation.ProductValidationService;
import org.softuni.productshop.validation.UserValidationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final ModelMapper mapper;
    private final UserValidationService userValidation;
    private final ProductValidationService productValidation;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            ProductRepository productRepository,
            UserService userService,
            UserValidationService userValidation,
            ProductValidationService productValidation,
            ModelMapper mapper
    ) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userService = userService;
        this.userValidation = userValidation;
        this.productValidation = productValidation;
        this.mapper = mapper;
    }

    @Override
    public void createOrder(String productId, String name) throws Exception {
        UserServiceModel userModel = userService.findUserByUserName(name);
        if(!userValidation.isValid(userModel)) {
            throw new Exception();
        }

        Product product = productRepository.findById(productId)
                .filter(productValidation::isValid)
                .orElseThrow(Exception::new);

        User user = new User();
        user.setId(userModel.getId());
        Order order = new Order();
        order.setProduct(product);
        order.setUser(user);

        orderRepository.save(order);
    }

    @Override
    public List<OrderServiceModel> findAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(o -> mapper.map(o, OrderServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderServiceModel> findOrdersByCustomer(String username) {
        return orderRepository.findAllByUser_Username(username)
                .stream()
                .map(o -> mapper.map(o, OrderServiceModel.class))
                .collect(Collectors.toList());
    }
}
