package com.mygitgor.ecommerce_multivendor.service.impl;

import com.mygitgor.ecommerce_multivendor.controller.DTOs.request.CreateReviewRequest;
import com.mygitgor.ecommerce_multivendor.domain.Product;
import com.mygitgor.ecommerce_multivendor.domain.Review;
import com.mygitgor.ecommerce_multivendor.domain.Users;
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
    public Review createReview(CreateReviewRequest request, Users users, Product product) {
        Review review = new Review();
        review.setUsers(users);
        review.setProduct(product);
        review.setReviewText(request.getReviewText());
        review.setRating(request.getReviewRating());
        review.setProductImages(request.getProductImages());
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
        if(review.getUsers().getId().equals(userId)){
            review.setReviewText(reviewText);
            review.setRating(rating);
            return reviewRepository.save(review);
        }
        throw new Exception("you can't update this review");
    }

    @Override
    public void deleteReview(Long reviewId, Long userId) throws Exception {
        Review review = getReviewById(reviewId);
        if(review.getUsers().getId().equals(userId)){
            throw new Exception("you can't delete this review");
        }
        reviewRepository.delete(review);
    }

    @Override
    public Review getReviewById(Long reviewId) throws Exception {
        return reviewRepository.findById(reviewId)
                .orElseThrow(()-> new Exception("review not found"));
    }
}
