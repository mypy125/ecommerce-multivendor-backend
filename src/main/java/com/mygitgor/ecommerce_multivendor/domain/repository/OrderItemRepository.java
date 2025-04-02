package com.mygitgor.ecommerce_multivendor.domain.repository;

import com.mygitgor.ecommerce_multivendor.domain.model.OrderItem;

public interface OrderItemRepository {
    OrderItem save(OrderItem orderItem);
    OrderItem findById(Long id);
}
