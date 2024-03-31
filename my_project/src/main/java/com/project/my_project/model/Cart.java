package com.project.my_project.model;

import jakarta.persistence.*;

import jakarta.persistence.Entity;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private MyUser user;

    @ManyToOne
    private Product product;

    private int quantity;


}