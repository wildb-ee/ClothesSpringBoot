package com.project.my_project.repository;

import com.project.my_project.model.Cart;
import com.project.my_project.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUser(MyUser user);

    void deleteByUser(MyUser user);


}