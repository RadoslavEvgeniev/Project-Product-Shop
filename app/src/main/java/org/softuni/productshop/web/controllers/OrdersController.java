package org.softuni.productshop.web.controllers;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.softuni.productshop.domain.models.view.OrderViewModel;
import org.softuni.productshop.web.annotations.PageTitle;
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
    ) {
        this.productService = productService;
        this.orderService = orderService;
        this.mapper = modelMapper;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PageTitle("All Orders")
    public ModelAndView getAllOrders(ModelAndView modelAndView) {
        List<OrderViewModel> orderViewModels = orderService.findAllOrders()
                .stream()
                .map(o -> mapper.map(o, OrderViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("orders", orderViewModels);

        return view("order/all-orders", modelAndView);
    }

    @GetMapping("/all/details/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PageTitle("Orders Details")
    public ModelAndView allOrderDetails(@PathVariable String id, ModelAndView modelAndView) {
        OrderViewModel orderViewModel = this.mapper.map(this.orderService.findOrderById(id), OrderViewModel.class);
        modelAndView.addObject("order", orderViewModel);

        return super.view("order/order-details", modelAndView);
    }

    @GetMapping("/my")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("My Orders")
    public ModelAndView getMyOrders(ModelAndView modelAndView, Principal principal) {
        List<OrderViewModel> orderViewModels = orderService.findOrdersByCustomer(principal.getName())
                .stream()
                .map(o -> mapper.map(o, OrderViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("orders", orderViewModels);

        return view("order/all-orders", modelAndView);
    }

    @GetMapping("/my/details/{id}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Orders Details")
    public ModelAndView myOrderDetails(@PathVariable String id, ModelAndView modelAndView) {
        OrderViewModel orderViewModel = this.mapper.map(this.orderService.findOrderById(id), OrderViewModel.class);
        modelAndView.addObject("order", orderViewModel);

        return super.view("order/order-details", modelAndView);
    }
}
