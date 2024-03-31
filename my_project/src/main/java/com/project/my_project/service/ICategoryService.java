package com.project.my_project.service;

import com.project.my_project.dtos.CategoryForm;
import com.project.my_project.model.Category;

import java.util.List;

public interface ICategoryService {
    Category findByName(String name);
    List<Category> getAllCategories();

    void addCategory(CategoryForm category);

    Category getCategoryByCode(String code);

    void updateCategory(String code, Category category);

    void deleteCategory(String code);
}
