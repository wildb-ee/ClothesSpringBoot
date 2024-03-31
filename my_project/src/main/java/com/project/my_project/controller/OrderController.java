package com.project.my_project.controller;

import com.project.my_project.dtos.TransactionForm;
import com.project.my_project.model.MyOrder;
import com.project.my_project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public String showCreateOrderForm(Model model) {
        model.addAttribute("transactionForm", new TransactionForm());
        return "create_order";
    }

    @PostMapping("/create")
    public String createOrder(@ModelAttribute("transactionForm") TransactionForm transactionForm,
                              @AuthenticationPrincipal UserDetails currentUser) {
        MyOrder order = orderService.createOrderForCurrentUser(currentUser.getUsername(), transactionForm);
        return "redirect:user/order/confirmation";
    }

    @GetMapping("/all")
    public String getUserOrders(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        List<MyOrder> userOrders = orderService.getUserOrders(currentUser.getUsername());
        model.addAttribute("userOrders", userOrders);
        return "user_orders";
    }

    @RequestMapping("/confirmation")
    public String orderConfirmation() {
        return "order_confirmation";
    }

}