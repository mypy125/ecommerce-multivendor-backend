package com.mygitgor.ecommerce_multivendor.application.service;

import com.mygitgor.ecommerce_multivendor.domain.User;
import com.mygitgor.ecommerce_multivendor.domain.costant.OrderStatus;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.*;

import java.util.List;
import java.util.Set;

public interface OrderService {
    Set<OrderEntity> createOrder(User user, AddressEntity shippingAddress, CartEntity cart);
    OrderEntity findOrderById(Long id) throws Exception;
    List<OrderEntity>usersOrderHistory(Long userId);
    List<OrderEntity>sellersOrder(Long sellerId);
    OrderEntity updateOrderStatus(Long orderId, OrderStatus status) throws Exception;
    OrderEntity cancelOrder(Long orderId, User user) throws Exception;
    OrderItemEntity getOrderItemById(Long id) throws Exception;
}
