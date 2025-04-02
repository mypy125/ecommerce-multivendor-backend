package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.repository.ProductRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.ProductJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductJpaRepository jpaRepository;
    private final ProductMapper productMapper;
}
