package com.mygitgor.ecommerce_multivendor.service;

import com.mygitgor.ecommerce_multivendor.controller.DTOs.request.CreateReviewRequest;
import com.mygitgor.ecommerce_multivendor.domain.Product;
import com.mygitgor.ecommerce_multivendor.domain.Review;
import com.mygitgor.ecommerce_multivendor.domain.Users;

import java.util.List;

public interface ReviewService {
    Review createReview(CreateReviewRequest request,
                        Users users,
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
