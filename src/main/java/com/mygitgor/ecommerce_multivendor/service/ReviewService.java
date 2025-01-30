package com.mygitgor.ecommerce_multivendor.service;

import com.mygitgor.ecommerce_multivendor.controller.DTOs.request.CreateReviewRequest;
import com.mygitgor.ecommerce_multivendor.domain.Product;
import com.mygitgor.ecommerce_multivendor.domain.Review;
import com.mygitgor.ecommerce_multivendor.domain.User;

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
    );

    void deleteReview(Long reviewId, Long userId);
}
