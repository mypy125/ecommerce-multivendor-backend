package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.model.Cart;
import com.mygitgor.ecommerce_multivendor.domain.repository.CartRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.CartEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.CartJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CartRepositoryImpl implements CartRepository {
    private final CartJpaRepository jpaRepository;
    private final CartMapper cartMapper;

    @Override
    public Cart findByUserId(Long userId) {
        return jpaRepository.findByUserId(userId)
                .map(cartMapper::toDomain)
                .orElseThrow(() -> new RuntimeException("Cart not found for userId: " + userId));
    }

    @Override
    public Cart save(Cart cart) {
        CartEntity entity = cartMapper.toEntity(cart);
        return cartMapper.toDomain(jpaRepository.save(entity));
    }
}
