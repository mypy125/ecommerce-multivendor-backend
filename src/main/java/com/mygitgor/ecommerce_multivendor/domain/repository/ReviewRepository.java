package com.mygitgor.ecommerce_multivendor.domain.repository;

import com.mygitgor.ecommerce_multivendor.domain.model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    Review save(Review review);
    Optional<Review> findById(Long id);
    List<Review> findByProductId(Long productId);
    void delete(Review review);
}
