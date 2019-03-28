package org.softuni.productshop.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.productshop.domain.models.rest.ProductOrderRequestModel;
import org.softuni.productshop.domain.models.service.ProductServiceModel;
import org.softuni.productshop.domain.models.view.ProductDetailsViewModel;
import org.softuni.productshop.service.OrderService;
import org.softuni.productshop.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

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
        this.orderService=orderService;
        this.mapper = modelMapper;
    }

    @GetMapping("/product/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView orderProduct(@PathVariable String id, ModelAndView modelAndView) {
        ProductServiceModel serviceModel = productService.findProductById(id);
        ProductDetailsViewModel viewModel = mapper.map(serviceModel, ProductDetailsViewModel.class);
        modelAndView.addObject("product", viewModel);
        return super.view("order/product", modelAndView);
    }
}
