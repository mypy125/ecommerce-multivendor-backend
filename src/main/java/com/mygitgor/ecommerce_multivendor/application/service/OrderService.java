package com.mygitgor.ecommerce_multivendor.application.service;

import com.mygitgor.ecommerce_multivendor.domain.costant.OrderStatus;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.*;

import java.util.List;
import java.util.Set;

public interface OrderService {
    Set<Order> createOrder(UserEntity user, AddressEntity shippingAddress, Cart cart);
    Order findOrderById(Long id) throws Exception;
    List<Order>usersOrderHistory(Long userId);
    List<Order>sellersOrder(Long sellerId);
    Order updateOrderStatus(Long orderId, OrderStatus status) throws Exception;
    Order cancelOrder(Long orderId, UserEntity user) throws Exception;
    OrderItem getOrderItemById(Long id) throws Exception;
}
