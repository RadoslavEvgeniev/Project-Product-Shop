package org.softuni.productshop.integration.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.productshop.domain.entities.Product;
import org.softuni.productshop.domain.models.service.CategoryServiceModel;
import org.softuni.productshop.domain.models.service.ProductServiceModel;
import org.softuni.productshop.repository.ProductRepository;
import org.softuni.productshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTests {
    @Autowired
    private ProductService service;

    @MockBean
    private ProductRepository mockProductRepository;

    @Test
    public void createProduct_whenValid_productCreated() {
        ProductServiceModel product = new ProductServiceModel();
        product.setCategories(List.of(new CategoryServiceModel()));
        when(mockProductRepository.save(any()))
                .thenReturn(new Product());

        service.createProduct(product);
        verify(mockProductRepository)
              .save(any());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createProduct_whenNull_throw() {
        service.createProduct(null);
        verify(mockProductRepository)
                .save(any());
    }
}
