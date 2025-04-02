package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.repository.SellerRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.SellerJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.SellerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SellerRepositoryImpl implements SellerRepository {
    private final SellerJpaRepository jpaRepository;
    private final SellerMapper sellerMapper;
}
