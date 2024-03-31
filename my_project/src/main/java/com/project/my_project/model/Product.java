package com.project.my_project.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Brand brand;

    private double price;

    @ManyToOne
    private Category category;

    private String size;
    private String color;
    private String image;

    @Column(length = 1000)
    private String description;

    private int stockQuantity;

    private String productCode = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
}