package com.project.my_project.repository;

import com.project.my_project.model.MyUser;
import com.project.my_project.model.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<MyOrder, Long> {
    List<MyOrder> findByUser(MyUser user);
}