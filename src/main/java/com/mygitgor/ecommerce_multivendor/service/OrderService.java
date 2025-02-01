package com.mygitgor.ecommerce_multivendor.service;

import com.mygitgor.ecommerce_multivendor.domain.*;
import com.mygitgor.ecommerce_multivendor.domain.costant.OrderStatus;

import java.util.List;
import java.util.Set;

public interface OrderService {
    Set<Order> createOrder(Users users, Address shippingAddress, Cart cart);
    Order findOrderById(Long id) throws Exception;
    List<Order>usersOrderHistory(Long userId);
    List<Order>sellersOrder(Long sellerId);
    Order updateOrderStatus(Long orderId, OrderStatus status) throws Exception;
    Order cancelOrder(Long orderId, Users users) throws Exception;
    OrderItem getOrderItemById(Long id) throws Exception;
}
