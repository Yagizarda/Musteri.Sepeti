package com.example.shopping.entity;

import javax.persistence.*;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "customer_order")
public class Order extends BaseEntity {

    @ManyToOne
    private Customer customer;

    @OneToMany
    private List<Product> products;

    private double totalPrice;

	public void setCustomer(com.example.shopping.service.impl.Customer customer2) {
		// TODO Auto-generated method stub
		
	}

	public void addOrderItem(OrderItem orderItem) {
		// TODO Auto-generated method stub
		
	}

	public void setCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		
	}

	public void setOrderDate(LocalDateTime now) {
		// TODO Auto-generated method stub
		
	}

	public void setCustomerId1(Long customerId) {
		// TODO Auto-generated method stub
		
	}

  
}