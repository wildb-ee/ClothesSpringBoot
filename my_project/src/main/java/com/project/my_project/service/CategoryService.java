package com.project.my_project.service;

import com.project.my_project.dtos.CategoryForm;
import com.project.my_project.model.Category;
import com.project.my_project.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements  ICategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void addCategory(CategoryForm categoryForm) {
        Category category = new Category();
        category.setName(categoryForm.getName());
        category.setDescription(categoryForm.getDescription());
        categoryRepository.save(category);

    }

    @Override
    public Category getCategoryByCode(String code) {
        return categoryRepository.findByCategoryCode(code);
    }

    @Override
    public void updateCategory(String code, Category category) {
        Category existingCategory = categoryRepository.findByCategoryCode(code);

        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());

        categoryRepository.save(existingCategory);
    }

    @Transactional
    @Override
    public void deleteCategory(String categoryCode) {
        categoryRepository.deleteByCategoryCode(categoryCode);
    }
}
