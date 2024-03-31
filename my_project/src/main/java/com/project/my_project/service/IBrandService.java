package com.project.my_project.service;

import com.project.my_project.dtos.BrandForm;
import com.project.my_project.model.Brand;

import java.util.List;

public interface IBrandService {
    Brand findByName(String name);

    List<Brand> getAllBrands();

    Brand getBrandByCode(String brand);

    void addBrand(BrandForm brandForm);

    void updateBrand(String code, Brand brandForm);

    void deleteBrand(String code);
}
