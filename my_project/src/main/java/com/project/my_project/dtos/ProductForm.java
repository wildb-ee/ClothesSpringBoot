package com.project.my_project.dtos;

import com.project.my_project.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductForm {

    private String name;
    private String image;
    private String brand;
    private double price;
    private String category;
    private String size;
    private String color;
    private String description;
    private int stockQuantity;

}
