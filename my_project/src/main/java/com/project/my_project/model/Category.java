package com.project.my_project.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryCode = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
    @Column(unique = true)
    private String name;
    private String description;

}