package com.project.my_project.service;

import com.project.my_project.model.Cart;
import com.project.my_project.model.MyUser;

import java.util.List;

public interface ICartService {
    void addToCart(Long productId, int quantity, MyUser user);
    List<Cart> getUserCart(MyUser user);
    void removeFromCart(Long cartItemId);
    void clearUserCart(MyUser user);
}
