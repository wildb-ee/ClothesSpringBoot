package com.project.my_project.repository;

import com.project.my_project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);

    Category findByCategoryCode(String categoryCode);

    void deleteByCategoryCode(String categoryCode);
}