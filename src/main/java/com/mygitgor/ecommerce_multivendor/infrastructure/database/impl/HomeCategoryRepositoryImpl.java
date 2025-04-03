package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.model.HomeCategory;
import com.mygitgor.ecommerce_multivendor.domain.repository.HomeCategoryRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.HomeCategoryEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.HomeCategoryJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.HomeCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class HomeCategoryRepositoryImpl implements HomeCategoryRepository {
    private final HomeCategoryJpaRepository jpaRepository;
    private final HomeCategoryMapper homeCategoryMapper;

    @Override
    public HomeCategory save(HomeCategory homeCategory) {
        HomeCategoryEntity entity = homeCategoryMapper.toEntity(homeCategory);
        HomeCategoryEntity savedEntity = jpaRepository.save(entity);
        return homeCategoryMapper.toDomain(savedEntity);
    }

    @Override
    public List<HomeCategory> saveAll(List<HomeCategory> homeCategories) {
        List<HomeCategoryEntity> entities = homeCategoryMapper.toEntity(homeCategories);
        List<HomeCategoryEntity> savedEntities = jpaRepository.saveAll(entities);
        return homeCategoryMapper.toDomain(savedEntities);
    }

    @Override
    public List<HomeCategory> findAll() {
        List<HomeCategoryEntity> entities = jpaRepository.findAll();
        return homeCategoryMapper.toDomain(entities);
    }

    @Override
    public Optional<HomeCategory> findById(Long id) {
        return jpaRepository.findById(id)
                .map(homeCategoryMapper::toDomain);
    }
}
