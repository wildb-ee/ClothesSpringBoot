package com.project.my_project.service;

import com.project.my_project.model.Cart;
import com.project.my_project.model.MyUser;
import com.project.my_project.model.Product;
import com.project.my_project.repository.CartRepository;
import com.project.my_project.repository.MyUserRepository;
import com.project.my_project.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MyUserRepository userRepository;

    public List<Cart> getUserCart(String username) {
        Optional<MyUser> user  = userRepository.findByUsername(username);
        if(user.isPresent()){
            return cartRepository.findByUser(user.get());
        }
        else{
            throw new RuntimeException("No such User");
        }

    }

    public void addToCart(String productCode, int quantity, String username) {
        Optional<MyUser> user  = userRepository.findByUsername(username);

        if(user.isPresent()){
            Cart cartItem = new Cart();
            cartItem.setProduct(productRepository.findByProductCode(productCode));
            cartItem.setQuantity(quantity);
            cartItem.setUser(user.get());
            cartRepository.save(cartItem);
        }
        else{
            throw new RuntimeException("No such User");
        }


    }

    public void removeFromCart(Long cartItemId) {
        cartRepository.deleteById(cartItemId);
    }

    @Transactional
    public void clearUserCart(String username) {
        Optional<MyUser> user  = userRepository.findByUsername(username);
        if(user.isPresent()){
            cartRepository.deleteByUser(user.get());
        }
        else{
            throw new RuntimeException("No such User");
        }


    }
}