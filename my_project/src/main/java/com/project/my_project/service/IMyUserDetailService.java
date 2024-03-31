package com.project.my_project.service;

import com.project.my_project.dtos.UserProfileDto;
import com.project.my_project.dtos.UserRegistrationDto;
import com.project.my_project.model.MyUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IMyUserDetailService extends UserDetailsService {
    MyUser save(UserRegistrationDto registrationDto);

    boolean changeUserPassword(String username, String oldPassword, String newPassword);

    MyUser saveProfile(UserProfileDto profileDto);

    MyUser getByUserName(String username);

    UserProfileDto getProfileByUsername(String username);


    List<MyUser> getAllUsers();

    void blockUser(String code);

    void deleteUser(String userCode);

    void unblockUser(String userCode);
}