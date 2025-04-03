package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.model.Deal;
import com.mygitgor.ecommerce_multivendor.domain.repository.DealRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.DealEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.DealJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.DealMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class DealRepositoryImpl implements DealRepository {
    private final DealJpaRepository jpaRepository;
    private final DealMapper dealMapper;

    @Override
    public List<Deal> findAll() {
        List<DealEntity> entities = jpaRepository.findAll();
        return entities.stream()
                .map(dealMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Deal save(Deal deal) {
        DealEntity entity = dealMapper.toEntity(deal);
        DealEntity savedEntity = jpaRepository.save(entity);
        return dealMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Deal> findById(Long id) {
        return jpaRepository.findById(id)
                .map(dealMapper::toDomain);
    }

    @Override
    public void delete(Deal deal) {
        DealEntity entity = dealMapper.toEntity(deal);
        jpaRepository.delete(entity);
    }

    @Override
    public List<Deal> saveAll(List<Deal> deals) {
        List<DealEntity> dealEntities = dealMapper.toEntity(deals);
        List<DealEntity> savedEntities = jpaRepository.saveAll(dealEntities);
        return dealMapper.toDomain(savedEntities);
    }
}
