package com.project.my_project.controller;


import com.project.my_project.dtos.PasswordForm;
import com.project.my_project.dtos.UserProfileDto;
import com.project.my_project.model.MyUser;
import com.project.my_project.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user/profile")
public class ProfileController {

    @Autowired
    private MyUserDetailService userService;

    @GetMapping("/get")
    public String viewProfile(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        MyUser user = userService.getByUserName(currentUser.getUsername());
        model.addAttribute("user", user);
        return "profile_view";
    }

    @GetMapping("/edit")
    public String showEditProfileForm(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        UserProfileDto user = userService.getProfileByUsername(currentUser.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("passwordForm", new PasswordForm());
        return "edit_profile";
    }

    @PostMapping("/edit")
    public String editProfile( @ModelAttribute("user") UserProfileDto user,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit_profile";
        }
        userService.saveProfile(user);
        return "redirect:/user/profile/get";
    }

    @PostMapping("/change-password")
    public String changePassword(@ModelAttribute("passwordForm") PasswordForm passwordForm,
                                 BindingResult bindingResult,
                                 @AuthenticationPrincipal UserDetails currentUser,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            return "edit_profile";
        }
        if (!userService.changeUserPassword(currentUser.getUsername(), passwordForm.getOldPassword(), passwordForm.getNewPassword())) {
            model.addAttribute("error", "Failed to change password. Please make sure your old password is correct.");
            return "edit_profile";
        }
        return "redirect:/logout"; // Logout after password change
    }
}