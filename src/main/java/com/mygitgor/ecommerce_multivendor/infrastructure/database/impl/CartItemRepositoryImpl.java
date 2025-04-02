package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.repository.CartItemRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.CartItemJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.CartItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CartItemRepositoryImpl implements CartItemRepository {
    private final CartItemJpaRepository jpaRepository;
    private final CartItemMapper cartItemMapper;
}
