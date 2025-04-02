package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.repository.OrderItemRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.OrderItemJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.OrderItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderItemRepositoryImpl implements OrderItemRepository {
    private final OrderItemJpaRepository jpaRepository;
    private final OrderItemMapper orderItemMapper;
}
