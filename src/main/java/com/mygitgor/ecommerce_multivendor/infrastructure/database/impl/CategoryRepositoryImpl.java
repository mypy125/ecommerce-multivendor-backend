package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.model.Category;
import com.mygitgor.ecommerce_multivendor.domain.repository.CategoryRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.CategoryEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.CategoryJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {
    private final CategoryJpaRepository jpaRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Category findByCategoryId(String categoryId) {
        CategoryEntity entity = jpaRepository.findByCategoryId(categoryId);
        return entity != null ? categoryMapper.toDomain(entity) : null;
    }

    @Override
    public Category save(Category category) {
        CategoryEntity entity = categoryMapper.toEntity(category);
        CategoryEntity savedEntity = jpaRepository.save(entity);
        return categoryMapper.toDomain(savedEntity);
    }
}
