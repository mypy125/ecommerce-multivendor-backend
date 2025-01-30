package com.mygitgor.ecommerce_multivendor.service.impl;

import com.mygitgor.ecommerce_multivendor.controller.DTOs.request.CreateReviewRequest;
import com.mygitgor.ecommerce_multivendor.domain.Product;
import com.mygitgor.ecommerce_multivendor.domain.Review;
import com.mygitgor.ecommerce_multivendor.domain.User;
import com.mygitgor.ecommerce_multivendor.repository.ReviewRepository;
import com.mygitgor.ecommerce_multivendor.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    
    @Override
    public Review createReview(CreateReviewRequest request, User user, Product product) {
        return null;
    }

    @Override
    public List<Review> getReviewByProductId(Long productId) {
        return List.of();
    }

    @Override
    public Review updateReview(Long reviewId, String reviewText, double rating, Long userId) {
        return null;
    }

    @Override
    public void deleteReview(Long reviewId, Long userId) {

    }
}
