package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.repository.DealRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.DealJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.DealMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DealRepositoryImpl implements DealRepository {
    private final DealJpaRepository jpaRepository;
    private final DealMapper dealMapper;
}
