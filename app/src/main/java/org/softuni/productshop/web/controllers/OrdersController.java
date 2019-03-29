package org.softuni.productshop.web.controllers;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.softuni.productshop.domain.models.view.OrderViewModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import org.modelmapper.ModelMapper;

import org.softuni.productshop.domain.models.service.ProductServiceModel;
import org.softuni.productshop.domain.models.view.ProductDetailsViewModel;
import org.softuni.productshop.service.OrderService;
import org.softuni.productshop.service.ProductService;

@Controller
@RequestMapping("/order")
public class OrdersController extends BaseController {
    private final ProductService productService;
    private final OrderService orderService;
    private final ModelMapper mapper;

    public OrdersController(
        ProductService productService,
        OrderService orderService,
        ModelMapper modelMapper
    ){
        this.productService = productService;
        this.orderService = orderService;
        this.mapper = modelMapper;
    }

    @GetMapping("/product/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView orderProduct(@PathVariable String id, ModelAndView modelAndView) {
        ProductServiceModel serviceModel = productService.findProductById(id);
        ProductDetailsViewModel viewModel = mapper.map(serviceModel, ProductDetailsViewModel.class);
        modelAndView.addObject("product", viewModel);
        return view("order/product", modelAndView);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView getAllOrders(ModelAndView modelAndView) {
        List<OrderViewModel> viewModels = orderService.findAllOrders()
                .stream()
                .map(o -> mapper.map(o, OrderViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("orders", viewModels);
        return view("order/list-orders", modelAndView);
    }

    @GetMapping("/customer")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getCustomerOrders(ModelAndView modelAndView, Principal principal) {
        String username = principal.getName();
        List<OrderViewModel> viewModels = orderService.findOrdersByCustomer(username)
                .stream()
                .map(o -> mapper.map(o, OrderViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("orders", viewModels);

        return view("order/list-orders", modelAndView);
    }
}
