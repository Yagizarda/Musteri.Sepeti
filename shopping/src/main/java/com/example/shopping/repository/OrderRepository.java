package com.example.shopping.repository;

import com.example.shopping.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByIdAndCustomer_Id(Long id, Long customerId);
    List<Order> findAllByCustomer_Id(Long customerId);
	Object findByIdAndCustomerId(Long orderId, Long customerId);
	Order findByIdAndCustomerId1(Long orderId, Long customerId);
}
