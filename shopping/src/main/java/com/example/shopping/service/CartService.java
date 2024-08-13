package com.example.shopping.service;

import com.example.shopping.entity.Cart;

public interface CartService {
    Cart getCart(Long customerId);
    Cart updateCart(Long customerId, Cart cart);
    void emptyCart(Long customerId);
    Cart addProductToCart(Long customerId, Long productId);
    Cart removeProductFromCart(Long customerId, Long productId);
}
