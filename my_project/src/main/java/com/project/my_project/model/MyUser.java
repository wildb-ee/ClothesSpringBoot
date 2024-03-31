package com.project.my_project.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String role;
    @Column(unique = true)
    private String username;
    private String location;
    private boolean blocked = false;
    private String lastRole;

    private String userCode = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);

    public MyUser(String firstName, String lastName, String email, String phone, String password, String role, String username, String location) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.username = username;
        this.location = location;
    }
}
