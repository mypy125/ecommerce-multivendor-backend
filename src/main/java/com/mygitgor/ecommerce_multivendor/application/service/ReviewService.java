package com.mygitgor.ecommerce_multivendor.application.service;

import com.mygitgor.ecommerce_multivendor.api.DTOs.request.CreateReviewRequest;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.Product;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.Review;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.UserEntity;

import java.util.List;

public interface ReviewService {
    Review createReview(CreateReviewRequest request,
                        UserEntity user,
                        Product product
    );

    List<Review> getReviewByProductId(Long productId);

    Review updateReview(Long reviewId,
                        String reviewText,
                        double rating,
                        Long userId
    ) throws Exception;

    void deleteReview(Long reviewId, Long userId) throws Exception;

    Review getReviewById(Long reviewId) throws Exception;
}
