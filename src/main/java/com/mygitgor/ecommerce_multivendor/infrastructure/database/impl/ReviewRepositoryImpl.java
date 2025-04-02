package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.repository.ReviewRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.ReviewJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepository {
    private final ReviewJpaRepository jpaRepository;
    private final ReviewMapper reviewMapper;
}
