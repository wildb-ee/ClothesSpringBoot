package com.project.my_project.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordForm {

    private String oldPassword;

    private String newPassword;

    private String newPasswordRepeat;


}