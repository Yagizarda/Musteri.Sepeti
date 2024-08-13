package com.example.shopping.service;

import com.example.shopping.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer addCustomer(Customer customer);
    Customer getCustomer(Long id);
    List<Customer> getAllCustomers();
}
