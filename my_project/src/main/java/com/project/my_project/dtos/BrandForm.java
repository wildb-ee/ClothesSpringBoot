package com.project.my_project.dtos;


import com.project.my_project.model.Brand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BrandForm {

    private String name;
    private String description;

    public BrandForm(Brand brand) {
        this.name = brand.getName();
        this.description = brand.getDescription();
    }

}
