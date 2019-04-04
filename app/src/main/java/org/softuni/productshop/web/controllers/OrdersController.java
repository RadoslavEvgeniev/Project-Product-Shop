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
@RequestMapping("/orders")
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

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView getAllOrders(ModelAndView modelAndView) {
        List<OrderViewModel> viewModels = orderService.findAllOrders()
                .stream()
                .map(o -> mapper.map(o, OrderViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("orders", viewModels);

        return view("order/all-orders", modelAndView);
    }

    @GetMapping("/all/details/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView allOrderDetails(@PathVariable String id, ModelAndView modelAndView) {
        modelAndView.addObject("order", this.mapper.map(this.orderService.findOrderById(id), OrderViewModel.class));

        return super.view("order/order-details", modelAndView);
    }

    @GetMapping("/my")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getMyOrders(ModelAndView modelAndView, Principal principal) {
        List<OrderViewModel> viewModels = orderService.findOrdersByCustomer(principal.getName())
                .stream()
                .map(o -> mapper.map(o, OrderViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("orders", viewModels);

        return view("order/all-orders", modelAndView);
    }

    @GetMapping("/my/details/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView myOrderDetails(@PathVariable String id, ModelAndView modelAndView) {
        modelAndView.addObject("order", this.mapper.map(this.orderService.findOrderById(id), OrderViewModel.class));

        return super.view("order/order-details", modelAndView);
    }

//    @GetMapping("/customer")
//    @PreAuthorize("isAuthenticated()")
//    public ModelAndView getCustomerOrders(ModelAndView modelAndView, Principal principal) {
//        String username = principal.getName();
//        List<OrderViewModel> viewModels = orderService.findOrdersByCustomer(username)
//                .stream()
//                .map(o -> mapper.map(o, OrderViewModel.class))
//                .collect(Collectors.toList());
//        modelAndView.addObject("orders", viewModels);
//
//        return view("order/list-orders", modelAndView);
//    }
}
