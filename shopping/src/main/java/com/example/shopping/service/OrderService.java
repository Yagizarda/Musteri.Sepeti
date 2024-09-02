package com.example.shopping.service;

import com.example.shopping.entity.Order;

import java.util.List;

public interface OrderService {
    Order placeOrder(Long customerId, Order order);
    Order getOrderForCode(Long orderId, Long customerId);
    List<Order> getAllOrdersForCustomer(Long customerId);
}