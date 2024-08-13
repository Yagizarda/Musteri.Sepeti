package com.example.shopping.controller;

import com.example.shopping.entity.Cart;
import com.example.shopping.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{customerId}")
    public Cart getCart(@PathVariable Long customerId) {
        return cartService.getCart(customerId);
    }

    @PutMapping("/{customerId}")
    public Cart updateCart(@PathVariable Long customerId, @RequestBody Cart cart) {
        return cartService.updateCart(customerId, cart);
    }

    @DeleteMapping("/{customerId}")
    public void emptyCart(@PathVariable Long customerId) {
        cartService.emptyCart(customerId);
    }

    @PostMapping("/{customerId}/products/{productId}")
    public Cart addProductToCart(@PathVariable Long customerId, @PathVariable Long productId) {
        return cartService.addProductToCart(customerId, productId);
    }

    @DeleteMapping("/{customerId}/products/{productId}")
    public Cart removeProductFromCart(@PathVariable Long customerId, @PathVariable Long productId) {
        return cartService.removeProductFromCart(customerId, productId);
    }
}
