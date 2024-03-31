package com.project.my_project.controller;

import com.project.my_project.dtos.ProductSearchCriteriaDto;
import com.project.my_project.model.Product;
import com.project.my_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/all")
    public String showAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "catalog";
    }

    @GetMapping("/search")
    public String getProductSearchForm(Model model) {
        model.addAttribute("criteria", new ProductSearchCriteriaDto());
        return "product_search";
    }

    @PostMapping("/search")
    public String searchProducts(@ModelAttribute("criteria") ProductSearchCriteriaDto criteria, Model model) {
        List<Product> searchResults = productService.searchProducts(criteria);
        model.addAttribute("searchResults", searchResults);
        return "product_search";
    }
}

