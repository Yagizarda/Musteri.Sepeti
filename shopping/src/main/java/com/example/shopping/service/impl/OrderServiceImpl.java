package com.example.shopping.service.impl;

import com.example.shopping.entity.Cart;
import com.example.shopping.entity.CartItem;
import com.example.shopping.entity.Order;
import com.example.shopping.entity.OrderItem;
import com.example.shopping.entity.Product;
import com.example.shopping.repository.OrderRepository;
import com.example.shopping.repository.ProductRepository;
import com.example.shopping.service.CartService;
import com.example.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartService cartService;

    @Override
    public Order placeOrder(Long customerId, Order order) {
        // Sepetteki ürünleri al
        Cart cart = cartService.getCartByCustomerId(customerId);
        List<CartItem> cartItems = cart.getCartItems();

        // Siparişin diğer detaylarını ayarla
        order.setCustomerId(customerId);
        order.setOrderDate(LocalDateTime.now());


        // Sipariş edilen ürünleri işleme al ve stok güncelle
        for (CartItem item : cartItems) {
            Product product = item.getProduct();
            int orderedQuantity = item.getQuantity();

            // Stok kontrolü
            if (product.getStock() < orderedQuantity) {
                throw new RuntimeException("Yeterli stok yok: " + product.getName());
            }

            // Stok güncelleme
            product.setStock(product.getStock() - orderedQuantity);
            productRepository.save(product);

            // Siparişe ürün ekleme
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(orderedQuantity);
            orderItem.setOrder(order);
            order.addOrderItem(orderItem);
        }

        // Siparişi kaydet
        orderRepository.save(order);

        // Sepeti temizle
        cartService.clearCart(customerId);

        return order;
    }

    @Override
    public Order getOrderForCode(Long orderId, Long customerId) {
        Order order = (Order) orderRepository.findByIdAndCustomerId(orderId, customerId);

        if (order == null) {
            throw new RuntimeException("Sipariş bulunamadı");
        }

        return order;
    }
    @Override
    public List<Order> getAllOrdersForCustomer(Long customerId) {
       
        return orderRepository.findAllByCustomer_Id(customerId);
    }
}
