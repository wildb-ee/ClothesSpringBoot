package com.project.my_project.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchCriteriaDto {

    private String name;
    private String brand;
    private String color;
    private String category;
    private String size;
    private Double price;

}