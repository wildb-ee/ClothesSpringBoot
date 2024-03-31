package com.project.my_project.controller;

import com.project.my_project.dtos.UserRegistrationDto;
import com.project.my_project.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {



    @GetMapping("/home")
    public String handleWelcome() {
        return "home";
    }

    @GetMapping("/about")
    public String handleAbout() {
        return "about";
    }

    @GetMapping("/contacts")
    public String handleContacts() {
        return "contacts";
    }


    @GetMapping("/admin/home")
    public String handleAdminHome() {
        return "home_admin";
    }

    @GetMapping("/user/home")
    public String handleUserHome() {
        return "home_user";
    }

}
