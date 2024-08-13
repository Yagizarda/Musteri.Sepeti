package com.example.shopping.service.impl;

import com.example.shopping.entity.Order;
import com.example.shopping.repository.OrderRepository;
import com.example.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order placeOrder(Long customerId, Order order) {
        order.setCustomer(new Customer());
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderForCode(Long orderId, Long customerId) {
        return orderRepository.findByIdAndCustomer_Id(orderId, customerId);
    }

    @Override
    public List<Order> getAllOrdersForCustomer(Long customerId) {
        return orderRepository.findAllByCustomer_Id(customerId);
    }
}
