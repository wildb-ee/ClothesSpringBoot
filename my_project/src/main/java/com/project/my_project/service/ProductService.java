package com.project.my_project.service;

import com.project.my_project.dtos.ProductForm;
import com.project.my_project.dtos.ProductSearchCriteriaDto;
import com.project.my_project.model.Brand;
import com.project.my_project.model.Category;
import com.project.my_project.model.Product;
import com.project.my_project.repository.CategoryRepository;
import com.project.my_project.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> searchProductsByName(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }

    @Override
    public List<Product> searchProducts(ProductSearchCriteriaDto criteria) {
        return productRepository.search(criteria.getName(), criteria.getBrand(), criteria.getColor(),
                criteria.getCategory(), criteria.getSize(), criteria.getPrice());
    }

    @Override
    public void addProduct(ProductForm productForm) {
        Product product = new Product();
        product.setName(productForm.getName());
        product.setPrice(productForm.getPrice());
        product.setSize(productForm.getSize());
        product.setColor(productForm.getColor());
        product.setImage(productForm.getImage());
        product.setDescription(productForm.getDescription());
        product.setStockQuantity(productForm.getStockQuantity());

        Brand brand = brandService.findByName(productForm.getBrand());
        Category category = categoryService.findByName(productForm.getCategory());


        product.setBrand(brand);
        product.setCategory(category);

        productRepository.save(product);
    }

    @Override
    public void updateProduct(String code, Product updatedProduct) {

        Product existingProduct = productRepository.findByProductCode(code);

        if (existingProduct != null) {

            existingProduct.setName(updatedProduct.getName());
            existingProduct.setBrand(brandService.getBrandByCode(updatedProduct.getBrand().getBrandCode()));
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setCategory(categoryService.getCategoryByCode(updatedProduct.getCategory().getCategoryCode()));
            existingProduct.setSize(updatedProduct.getSize());
            existingProduct.setColor(updatedProduct.getColor());
            existingProduct.setImage(updatedProduct.getImage());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setStockQuantity(updatedProduct.getStockQuantity());

            productRepository.save(existingProduct);
        } else {
            throw new RuntimeException("Product not found for code: " + code);
        }
    }

    @Override
    public Product getByProductCode(String code) {
        return productRepository.findByProductCode(code);
    }

    @Transactional
    @Override
    public void deleteProductByCode(String code) {
        productRepository.deleteByProductCode(code);
    }
}