package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.repository.OrderRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.OrderJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    private final OrderJpaRepository jpaRepository;
    private final OrderMapper orderMapper;
}
