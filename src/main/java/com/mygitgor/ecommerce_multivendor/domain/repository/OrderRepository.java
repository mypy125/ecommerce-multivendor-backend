package com.mygitgor.ecommerce_multivendor.domain.repository;

import com.mygitgor.ecommerce_multivendor.domain.model.Order;

import java.util.List;

public interface OrderRepository {
    Order findById(Long id) throws Exception;
    List<Order> findByUserId(Long userId);
    List<Order> findBySellerId(Long sellerId);
    Order save(Order order);
    void deleteById(Long id);
}
