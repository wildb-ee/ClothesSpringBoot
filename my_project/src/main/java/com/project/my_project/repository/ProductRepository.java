package com.project.my_project.repository;

import com.project.my_project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingIgnoreCase(String keyword);

    @Query("SELECT p FROM Product p LEFT JOIN p.category c WHERE "
            + "(:name IS NULL OR p.name LIKE %:name%) AND "
            + "(:brand IS NULL OR p.brand.name LIKE %:brand%) AND "
            + "(:color IS NULL OR p.color LIKE %:color%) AND "
            + "(:category IS NULL OR c.name LIKE %:category%) AND "
            + "(:size IS NULL OR p.size LIKE %:size%) AND "
            + "(:price IS NULL OR p.price = :price)")
    List<Product> search(String name, String brand, String color, String category, String size, Double price);


    Product findByProductCode(String productCode);

    void deleteByProductCode(String productCode);
}
