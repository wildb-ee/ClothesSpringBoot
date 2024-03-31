package com.project.my_project.service;

import com.project.my_project.dtos.ProductForm;
import com.project.my_project.dtos.ProductSearchCriteriaDto;
import com.project.my_project.model.Product;

import java.util.List;

public interface IProductService {

    List<Product> getAllProducts();

    List<Product> searchProductsByName(String keyword);
    List<Product> searchProducts(ProductSearchCriteriaDto criteria);

    void addProduct(ProductForm product);

    Product getByProductCode(String code);

    void deleteProductByCode(String code);

    void updateProduct(String code, Product updatedProduct);
}