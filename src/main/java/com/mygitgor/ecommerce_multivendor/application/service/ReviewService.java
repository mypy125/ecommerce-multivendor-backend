package com.mygitgor.ecommerce_multivendor.application.service;

import com.mygitgor.ecommerce_multivendor.api.DTOs.request.CreateReviewRequest;
import com.mygitgor.ecommerce_multivendor.domain.User;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.ProductEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.ReviewEntity;

import java.util.List;

public interface ReviewService {
    ReviewEntity createReview(CreateReviewRequest request,
                              User user,
                              ProductEntity product
    );

    List<ReviewEntity> getReviewByProductId(Long productId);

    ReviewEntity updateReview(Long reviewId,
                              String reviewText,
                              double rating,
                              Long userId
    ) throws Exception;

    void deleteReview(Long reviewId, Long userId) throws Exception;

    ReviewEntity getReviewById(Long reviewId) throws Exception;
}
