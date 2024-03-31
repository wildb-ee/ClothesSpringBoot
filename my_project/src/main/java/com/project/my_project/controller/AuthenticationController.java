package com.project.my_project.controller;

import com.project.my_project.dtos.UserRegistrationDto;
import com.project.my_project.model.MyUser;
import com.project.my_project.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenticationController {

    @Autowired
    private MyUserDetailService userService;

    @GetMapping("/login")
    public String handleLogin() {
        return "custom_login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userRegistrationDTO", new UserRegistrationDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("userRegistrationDTO")  UserRegistrationDto userRegistrationDTO,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        if (userService.isUserExists(userRegistrationDTO.getEmail())) {
            model.addAttribute("usernameError", "Username already exists");
            return "register";
        }

        userService.save(userRegistrationDTO);
        return "redirect:/login";
    }

}
