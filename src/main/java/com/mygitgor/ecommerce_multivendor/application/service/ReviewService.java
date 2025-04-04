package com.mygitgor.ecommerce_multivendor.application.service;

import com.mygitgor.ecommerce_multivendor.api.DTOs.request.CreateReviewRequest;
import com.mygitgor.ecommerce_multivendor.domain.model.Product;
import com.mygitgor.ecommerce_multivendor.domain.model.Review;
import com.mygitgor.ecommerce_multivendor.domain.model.User;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.ProductEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.ReviewEntity;

import java.util.List;

public interface ReviewService {
    Review createReview(CreateReviewRequest request,
                        User user,
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
