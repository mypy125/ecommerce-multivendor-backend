package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.repository.CartRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.CartJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CartRepositoryImpl implements CartRepository {
    private final CartJpaRepository jpaRepository;
    private final CartMapper cartMapper;
}
