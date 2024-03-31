package com.project.my_project.repository;

import com.project.my_project.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {

    Optional<MyUser> findByUsername(String username);

    MyUser findByUserCode(String code);

    void deleteByUserCode(String userCode);
}
