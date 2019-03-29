package org.softuni.productshop.service;

import org.softuni.productshop.domain.models.service.ProductServiceModel;

import java.util.List;

public interface ProductService {

    ProductServiceModel createProduct(ProductServiceModel productServiceModel);

    List<ProductServiceModel> findAllProducts();

    ProductServiceModel findProductById(String id);

    ProductServiceModel editProduct(String id, ProductServiceModel productServiceModel);

    void deleteProduct(String id);

    List<ProductServiceModel> findAllByCategory(String category);
}
