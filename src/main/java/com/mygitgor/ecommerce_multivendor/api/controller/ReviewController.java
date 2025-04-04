package com.mygitgor.ecommerce_multivendor.api.controller;


import com.mygitgor.ecommerce_multivendor.api.DTOs.request.CreateReviewRequest;
import com.mygitgor.ecommerce_multivendor.api.DTOs.response.ApiResponse;
import com.mygitgor.ecommerce_multivendor.domain.model.Product;
import com.mygitgor.ecommerce_multivendor.domain.model.Review;
import com.mygitgor.ecommerce_multivendor.domain.model.User;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.ProductEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.ReviewEntity;
import com.mygitgor.ecommerce_multivendor.application.service.ProductService;
import com.mygitgor.ecommerce_multivendor.application.service.ReviewService;
import com.mygitgor.ecommerce_multivendor.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {
    private final ReviewService reviewService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/products/{productId}/reviews")
    public ResponseEntity<List<Review>>getReviewsByProductId(@PathVariable Long productId)
    {
        List<Review> reviews = reviewService.getReviewByProductId(productId);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping("/products/{productId}/reviews")
    public ResponseEntity<Review>writeReview(@RequestBody CreateReviewRequest req,
                                                   @PathVariable Long productId,
                                                   @RequestHeader("Authorization")
                                                 String jwt) throws Exception
    {
        User user = userService.findByJwtToken(jwt);
        Product product = productService.findProductById(productId);
        Review review = reviewService.createReview(req, user,product);

        return ResponseEntity.ok(review);
    }

    @PatchMapping("/reviews/{reviewId}")
    public ResponseEntity<Review>updateReview(@RequestBody CreateReviewRequest req,
                                                    @PathVariable Long reviewId,
                                                    @RequestHeader("Authorization")
                                              String jwt) throws Exception
    {
        User user = userService.findByJwtToken(jwt);
        Review review = reviewService.updateReview(
                reviewId, req.getReviewText(),req.getReviewRating(), user.getId()
        );
        return ResponseEntity.ok(review);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<ApiResponse>deleteReview(@PathVariable Long reviewId,
                                                   @RequestHeader("Authorization")
                                                   String jwt) throws Exception
    {
        User user = userService.findByJwtToken(jwt);
        reviewService.deleteReview(reviewId, user.getId());

        ApiResponse response = new ApiResponse();
        response.setMessage("ReviewEntity deleted successfully");

        return ResponseEntity.ok(response);
    }

}
