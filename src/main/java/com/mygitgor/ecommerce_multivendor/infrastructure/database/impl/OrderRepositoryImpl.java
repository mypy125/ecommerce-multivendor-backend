package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.model.Order;
import com.mygitgor.ecommerce_multivendor.domain.repository.OrderRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.OrderEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.OrderJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    private final OrderJpaRepository jpaRepository;
    private final OrderMapper orderMapper;

    @Override
    public Order findById(Long id) throws Exception {
        return jpaRepository.findById(id)
                .map(orderMapper::toDomain)
                .orElseThrow(() -> new Exception("Order not found..."));
    }

    @Override
    public List<Order> findByUserId(Long userId) {
        return jpaRepository.findByUserId(userId).stream()
                .map(orderMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> findBySellerId(Long sellerId) {
        return jpaRepository.findBySellerId(sellerId).stream()
                .map(orderMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Order save(Order order) {
        OrderEntity savedEntity = jpaRepository.save(orderMapper.toEntity(order));
        return orderMapper.toDomain(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
