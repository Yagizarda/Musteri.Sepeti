package com.example.shopping.controller;

import com.example.shopping.entity.Order;
import com.example.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/{customerId}")
    public Order placeOrder(@PathVariable Long customerId, @RequestBody Order order) {
        return orderService.placeOrder(customerId, order);
    }

    @GetMapping("/{orderId}/customers/{customerId}")
    public Order getOrderForCode(@PathVariable Long orderId, @PathVariable Long customerId) {
        return orderService.getOrderForCode(orderId, customerId);
    }

    @GetMapping("/customers/{customerId}")
    public List<Order> getAllOrdersForCustomer(@PathVariable Long customerId) {
        return orderService.getAllOrdersForCustomer(customerId);
    }
}
