package com.project.my_project.service;

import com.project.my_project.dtos.BrandForm;
import com.project.my_project.model.Brand;
import com.project.my_project.model.Category;
import com.project.my_project.repository.BrandRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService implements IBrandService{

    @Autowired
    private BrandRepository brandRepository;
    @Override
    public Brand findByName(String name) {
        return brandRepository.findByName(name);
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getBrandByCode(String brandCode) {
        return brandRepository.findByBrandCode(brandCode);
    }

    @Override
    public void addBrand(BrandForm brandForm) {
        Brand brand = new Brand();
        brand.setName(brandForm.getName());
        brand.setDescription(brandForm.getDescription());
        brandRepository.save(brand);
    }

    @Override
    public void updateBrand(String code, Brand brand) {
        Brand newBrand = brandRepository.findByBrandCode(code);

        newBrand.setName(brand.getName());
        newBrand.setDescription(brand.getDescription());

        brandRepository.save(newBrand);
    }

    @Transactional
    @Override
    public void deleteBrand(String brandCode) {
        brandRepository.deleteByBrandCode(brandCode);
    }

}
