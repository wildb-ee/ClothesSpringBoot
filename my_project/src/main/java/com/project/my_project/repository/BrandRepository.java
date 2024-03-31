package com.project.my_project.repository;

import com.project.my_project.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findByName(String name);

    Brand findByBrandCode(String brandCode);

    void deleteByBrandCode(String brandCode);
}