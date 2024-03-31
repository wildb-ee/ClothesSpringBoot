package com.project.my_project.dtos;


import com.project.my_project.model.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryForm {
    private String name;
    private String description;

    public CategoryForm(Category category) {
        this.name = category.getName();
        this.description = category.getDescription();
    }

}
