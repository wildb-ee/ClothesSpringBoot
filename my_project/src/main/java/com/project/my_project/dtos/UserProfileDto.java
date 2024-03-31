package com.project.my_project.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String location;

}
