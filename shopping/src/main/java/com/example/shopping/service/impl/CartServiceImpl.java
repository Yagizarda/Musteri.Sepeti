package com.example.shopping.service.impl;

import com.example.shopping.entity.Cart;
import com.example.shopping.entity.Customer;
import com.example.shopping.entity.Product;
import com.example.shopping.repository.CartRepository;
import com.example.shopping.repository.CustomerRepository;
import com.example.shopping.repository.ProductRepository;
import com.example.shopping.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Cart getCart(Long customerId) {
        return cartRepository.findByCustomer_Id(customerId);
    }

    @Override
    public Cart updateCart(Long customerId, Cart cart) {
        Cart existingCart = cartRepository.findByCustomer_Id(customerId);
        existingCart.setProducts(cart.getProducts());
        existingCart.setTotalPrice(cart.getTotalPrice());
        return cartRepository.save(existingCart);
    }

    @Override
    public void emptyCart(Long customerId) {
        Cart cart = cartRepository.findByCustomer_Id(customerId);
        cart.getProducts().clear();
        cart.setTotalPrice(0);
        cartRepository.save(cart);
    }

    @Override
    public Cart addProductToCart(Long customerId, Long productId) {
        Cart cart = cartRepository.findByCustomer_Id(customerId);
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        cart.getProducts().add(product);
        cart.setTotalPrice(cart.getTotalPrice() + product.getPrice());
        return cartRepository.save(cart);
    }

    @Override
    public Cart removeProductFromCart(Long customerId, Long productId) {
        Cart cart = cartRepository.findByCustomer_Id(customerId);
        @SuppressWarnings("unchecked")
		Optional<Product> product = ((Optional<Product>) cart.getProducts()).stream().filter(p -> p.getId().equals(productId)).findFirst();
        if (product.isPresent()) {
            cart.getProducts().remove(product.get());
            cart.setTotalPrice(cart.getTotalPrice() - product.get().getPrice());
        }
        return cartRepository.save(cart);
    }
}
