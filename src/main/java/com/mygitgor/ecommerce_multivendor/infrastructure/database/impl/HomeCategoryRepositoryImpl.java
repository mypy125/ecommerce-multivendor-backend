package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.repository.HomeCategoryRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.HomeCategoryJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.HomeCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class HomeCategoryRepositoryImpl implements HomeCategoryRepository {
    private final HomeCategoryJpaRepository jpaRepository;
    private final HomeCategoryMapper homeCategoryMapper;
}
