package com.project.my_project.controller;


import com.project.my_project.dtos.BrandForm;
import com.project.my_project.dtos.CategoryForm;
import com.project.my_project.dtos.ProductForm;
import com.project.my_project.service.BrandService;
import com.project.my_project.service.CategoryService;
import com.project.my_project.service.MyUserDetailService;
import com.project.my_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.project.my_project.model.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private MyUserDetailService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @GetMapping("/panel")
    public String listProducts() {
        return "admin_panel";
    }

    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin_product";
    }

    @GetMapping("/products/add")
    public String showProductForm(Model model) {
        model.addAttribute("product", new ProductForm());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("brands", brandService.getAllBrands());

        return "admin_add_product";
    }

    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute("product") ProductForm product) {
        productService.addProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/products/edit/{code}")
    public String showEditProductForm(@PathVariable String code, Model model) {
        Product product = productService.getByProductCode(code);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("brands", brandService.getAllBrands() );
        return "admin_edit_product";
    }

    @PostMapping("/products/edit/{code}")
    public String editProduct(@PathVariable String code, @ModelAttribute("product") Product product) {
        productService.updateProduct(code, product);
        return "redirect:/admin/products";
    }

    @GetMapping("/products/delete/{code}")
    public String deleteProduct(@PathVariable String code) {
        productService.deleteProductByCode(code);
        return "redirect:/admin/products";
    }

    // User Management

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin_users";
    }

    @GetMapping("/users/block/{code}")
    public String blockUser(@PathVariable String code) {
        userService.blockUser(code);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/delete/{code}")
    public String deleteUser(@PathVariable String code) {
        userService.deleteUser(code);
        return "redirect:/admin/users";
    }


    @GetMapping("/users/unblock/{code}")
    public String unblockUser(@PathVariable String code) {
        userService.unblockUser(code);
        return "redirect:/admin/users";
    }


    // Category Management

    @GetMapping("/categories")
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin_categories";
    }

    @GetMapping("/categories/add")
    public String showCategoryForm(Model model) {
        model.addAttribute("category", new CategoryForm());
        return "admin_add_category";
    }

    @PostMapping("/categories/add")
    public String addCategory(@ModelAttribute("category") CategoryForm category) {
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/edit/{code}")
    public String showEditCategoryForm(@PathVariable String code, Model model) {
        Category category = categoryService.getCategoryByCode(code);
        model.addAttribute("category", category);
        return "admin_edit_category";
    }

    @PostMapping("/categories/edit/{code}")
    public String editCategory(@PathVariable String code, @ModelAttribute("category") Category category) {
        categoryService.updateCategory(code, category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/delete/{code}")
    public String deleteCategory(@PathVariable String code) {
        categoryService.deleteCategory(code);
        return "redirect:/admin/categories";
    }

    // Brand Management

    @GetMapping("/brands")
    public String listBrands(Model model) {
        model.addAttribute("brands", brandService.getAllBrands());
        return "admin_brands";
    }

    @GetMapping("/brands/add")
    public String showBrandForm(Model model) {
        model.addAttribute("brand", new BrandForm());
        return "admin_add_brand";
    }

    @PostMapping("/brands/add")
    public String addBrand(@ModelAttribute("brand") BrandForm brandForm) {
        brandService.addBrand(brandForm);
        return "redirect:/admin/brands";
    }

    @GetMapping("/brands/edit/{code}")
    public String showEditBrandForm(@PathVariable String code, Model model) {
        Brand brand = brandService.getBrandByCode(code);
        model.addAttribute("brand", brand);
        return "admin_edit_brand";
    }

    @PostMapping("/brands/edit/{code}")
    public String editBrand(@PathVariable String code, @ModelAttribute("brand") Brand brand) {
        brandService.updateBrand(code, brand);
        return "redirect:/admin/brands";
    }

    @GetMapping("/brands/delete/{code}")
    public String deleteBrand(@PathVariable String code) {
        brandService.deleteBrand(code);
        return "redirect:/admin/brands";
    }

}