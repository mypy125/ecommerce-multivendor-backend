package com.mygitgor.ecommerce_multivendor.application.impl;

import com.mygitgor.ecommerce_multivendor.api.DTOs.request.CreateReviewRequest;
import com.mygitgor.ecommerce_multivendor.domain.model.Product;
import com.mygitgor.ecommerce_multivendor.domain.model.Review;
import com.mygitgor.ecommerce_multivendor.domain.model.User;
import com.mygitgor.ecommerce_multivendor.domain.repository.ReviewRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.ProductEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.ReviewEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.ReviewJpaRepository;
import com.mygitgor.ecommerce_multivendor.application.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public Review createReview(CreateReviewRequest request, User user, Product product) {
        Review review = Review.builder()
                .user(user)
                .product(product)
                .reviewText(request.getReviewText())
                .rating(request.getReviewRating())
                .productImages(request.getProductImages())
                .build();

        product.getReviews().add(review);
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewByProductId(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    @Override
    public Review updateReview(Long reviewId, String reviewText, double rating, Long userId) throws Exception {
        Review review = getReviewById(reviewId);
        validateOwnership(review, userId);

        review.setReviewText(reviewText);
        review.setRating(rating);
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long reviewId, Long userId) throws Exception {
        Review review = getReviewById(reviewId);
        validateOwnership(review, userId);

        reviewRepository.delete(review);
    }

    @Override
    public Review getReviewById(Long reviewId) throws Exception {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new Exception("Review not found with id " + reviewId));
    }

    private void validateOwnership(Review review, Long userId) throws Exception {
        if (!review.getUser().getId().equals(userId)) {
            throw new Exception("You are not allowed to modify this review");
        }
    }
}
