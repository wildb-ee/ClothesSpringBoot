package com.project.my_project.controller;

import com.project.my_project.model.Cart;
import com.project.my_project.model.MyUser;
import com.project.my_project.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/user/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/view")
    public String viewCart(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        String username = userDetails.getUsername();
        List<Cart> cartItems = cartService.getUserCart(username);
        model.addAttribute("cartItems", cartItems);
        return "cart_view";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam String productCode, @RequestParam int quantity, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        cartService.addToCart(productCode, quantity, username);
        return "redirect:/user/cart/view";
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Long cartItemId) {
        cartService.removeFromCart(cartItemId);
        return "redirect:/user/cart/view";
    }

    @PostMapping("/clear")
    public String clearCart(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        cartService.clearUserCart(username);
        return "redirect:/user/cart/view";
    }
}
