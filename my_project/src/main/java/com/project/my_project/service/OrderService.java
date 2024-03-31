package com.project.my_project.service;

import com.project.my_project.dtos.TransactionForm;
import com.project.my_project.model.*;
import com.project.my_project.repository.OrderDetailRepository;
import com.project.my_project.repository.OrderRepository;
import com.project.my_project.repository.OrderStatusRepository;
import com.project.my_project.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private  CartService cartService;
    @Autowired
    private  OrderRepository orderRepository;
    @Autowired
    private  MyUserDetailService userDetailsService;
    @Autowired
    private  TransactionRepository transactionRepository;
    @Autowired
    private  OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;


    @Transactional
    public MyOrder createOrderForCurrentUser(String username, TransactionForm transactionForm) {
        List<Cart> cartItems = cartService.getUserCart(username);
        MyUser currentUser = userDetailsService.getByUserName(username);
        MyOrder order = new MyOrder();
        order.setUser(currentUser);
        order.setOrderNumber(generateOrderNumber());
        order.setOrderStatus(orderStatusRepository.findByName("Pending"));


        for (Cart cartItem : cartItems) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(cartItem.getProduct());
            orderDetail.setQuantity(cartItem.getQuantity());

            double totalPrice = cartItem.getProduct().getPrice() * cartItem.getQuantity();
            orderDetail.setTotalPrice(totalPrice);
            order.getOrderDetails().add(orderDetail);

            orderDetailRepository.save(orderDetail);
        }

        orderRepository.save(order);

        cartService.clearUserCart(username);

        return createOrderAndTransaction(order, transactionForm);
    }
    public List<MyOrder> getUserOrders(String username) {
        return orderRepository.findByUser(userDetailsService.getByUserName(username));
    }
    public MyOrder createOrderAndTransaction(MyOrder order, TransactionForm transactionForm) {
        Transaction transaction = new Transaction();
        transaction.setLastFourDigits(transactionForm.getLastFourDigits());
        transaction.setFirstName(transactionForm.getFirstName());
        transaction.setLastName(transactionForm.getLastName());
        transaction.setTimestamp(LocalDateTime.now());
        transactionRepository.save(transaction);


        order.setTransaction(transaction);


        return orderRepository.save(order);
    }

    private String generateOrderNumber() {
        return "ORDER_" + UUID.randomUUID().toString().replace("-", "");
    }
}