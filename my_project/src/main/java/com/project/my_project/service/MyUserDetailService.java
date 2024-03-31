package com.project.my_project.service;

import com.project.my_project.dtos.UserProfileDto;
import com.project.my_project.dtos.UserRegistrationDto;
import com.project.my_project.model.MyUser;
import com.project.my_project.repository.MyUserRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailService implements IMyUserDetailService {

    private final MyUserRepository repository;

    @Setter
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public MyUserDetailService(MyUserRepository repository, @Lazy BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder  = passwordEncoder;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> user = repository.findByUsername(username);
        if (user.isPresent()) {
            var userObj = user.get();
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles(getRoles(userObj))
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }


    public boolean isUserExists(String username) {
        Optional<MyUser> user = repository.findByUsername(username);
        return user.isPresent();
    }

    @Override
    public MyUser save(UserRegistrationDto registrationDto) {
        MyUser user = new MyUser(registrationDto.getFirstName(),registrationDto.getLastName(),
                registrationDto.getEmail(),registrationDto.getPhone(),
                passwordEncoder.encode(registrationDto.getPassword()), registrationDto.getRole(), registrationDto.getUsername(), registrationDto.getLocation());

        return repository.save(user);
    }



    @Override
    public UserProfileDto getProfileByUsername(String username) {
        Optional<MyUser> user = repository.findByUsername(username);
        if (user.isPresent()) {
            return convertToDto(user.get());
        }
        throw new RuntimeException("No Such User");
    }

    @Override
    public List<MyUser> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public void blockUser(String code) {
        MyUser user = repository.findByUserCode(code);

        if (user != null) {
            if(!user.getRole().contains("ADMIN")){
            user.setBlocked(true);
            user.setLastRole(user.getRole());
            user.setRole("BLOCKED");
            repository.save(user);
            }
            else {
                throw new IllegalArgumentException("Cannot block an admin" );
            }

        } else {
            throw new IllegalArgumentException("User not found with code: " + code);
        }
    }

    @Override
    public void deleteUser(String userCode) {
        MyUser user = repository.findByUserCode(userCode);
        if(!user.getRole().contains("ADMIN")){
            repository.deleteByUserCode(userCode);
        }
        else{
            throw new IllegalArgumentException("Cannot delete an admin" );
        }
    }

    @Override
    public void unblockUser(String userCode) {
        MyUser user = repository.findByUserCode(userCode);

        if (user != null) {
            user.setBlocked(false);
            user.setRole(user.getLastRole());
            repository.save(user);
        } else {
            throw new IllegalArgumentException("User not found with code: " + userCode);
        }
    }

    private UserProfileDto convertToDto(MyUser user) {
        UserProfileDto userProfileDto = new UserProfileDto();
        userProfileDto.setUsername(user.getUsername());
        userProfileDto.setFirstName(user.getFirstName());
        userProfileDto.setLastName(user.getLastName());
        userProfileDto.setEmail(user.getEmail());
        userProfileDto.setPhone(user.getPhone());
        userProfileDto.setLocation(user.getLocation());
        return userProfileDto;
    }
    @Override
    public MyUser saveProfile(UserProfileDto profileDto) {
        Optional<MyUser> user = repository.findByUsername(profileDto.getUsername());
        if (user.isPresent()) {
            MyUser changedUser = user.get();
            changedUser.setFirstName(profileDto.getFirstName());
            changedUser.setLastName(profileDto.getLastName());
            changedUser.setEmail(profileDto.getEmail());
            changedUser.setPhone(profileDto.getPhone());
            changedUser.setLocation(profileDto.getLocation());
            return repository.save(changedUser);
        }
        return null;
    }

    @Override
    public MyUser getByUserName(String username) {
        Optional<MyUser> user = repository.findByUsername(username);
        if(user.isPresent()){
            return user.get();
        }
        else{
            throw new RuntimeException("No Such User");
        }
    }
    private String[] getRoles(MyUser user) {
        if (user.getRole() == null) {
            return new String[]{"USER"};
        }
        return user.getRole().split(",");
    }

    @Override
    public boolean changeUserPassword(String username, String oldPassword, String newPassword) {
        Optional<MyUser> user = repository.findByUsername(username);
        if (user.isPresent() ){
            if(passwordEncoder.matches(oldPassword, user.get().getPassword())){
                MyUser changedUser = user.get();
                changedUser.setPassword(passwordEncoder.encode(newPassword));
                repository.save(changedUser);
                return true;
            }
        }
        return false;
    }
}
