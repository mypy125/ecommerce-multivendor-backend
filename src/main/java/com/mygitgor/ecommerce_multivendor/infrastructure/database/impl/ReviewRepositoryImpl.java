package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.model.Review;
import com.mygitgor.ecommerce_multivendor.domain.repository.ReviewRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.ReviewEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.ReviewJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepository {
    private final ReviewJpaRepository jpaRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public Review save(Review review) {
        ReviewEntity saved = jpaRepository.save(reviewMapper.toEntity(review));
        return reviewMapper.toDomain(saved);
    }

    @Override
    public Optional<Review> findById(Long id) {
        return jpaRepository.findById(id).map(reviewMapper::toDomain);
    }

    @Override
    public List<Review> findByProductId(Long productId) {
        return reviewMapper.toDomain(jpaRepository.findByProductId(productId));
    }

    @Override
    public void delete(Review review) {
        jpaRepository.delete(reviewMapper.toEntity(review));
    }
}
