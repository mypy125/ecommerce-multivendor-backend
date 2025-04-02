package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.repository.CategoryRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.CategoryJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {
    private final CategoryJpaRepository jpaRepository;
    private final CategoryMapper categoryMapper;
}
