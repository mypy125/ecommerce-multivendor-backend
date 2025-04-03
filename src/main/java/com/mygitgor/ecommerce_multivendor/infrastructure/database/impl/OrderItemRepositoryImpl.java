package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.model.OrderItem;
import com.mygitgor.ecommerce_multivendor.domain.repository.OrderItemRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.OrderItemEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.OrderItemJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.OrderItemMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderItemRepositoryImpl implements OrderItemRepository {
    private final OrderItemJpaRepository jpaRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public OrderItem save(OrderItem orderItem) {
        OrderItemEntity entity = orderItemMapper.toEntity(orderItem);
        OrderItemEntity savedEntity = jpaRepository.save(entity);
        return orderItemMapper.toDomain(savedEntity);
    }

    @Override
    public OrderItem findById(Long id) {
        return jpaRepository.findById(id)
                .map(orderItemMapper::toDomain)
                .orElseThrow(() -> new EntityNotFoundException("OrderItem not found with id " + id));
    }
}
