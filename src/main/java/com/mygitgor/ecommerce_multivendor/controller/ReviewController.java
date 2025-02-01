package com.mygitgor.ecommerce_multivendor.controller;


import com.mygitgor.ecommerce_multivendor.controller.DTOs.request.CreateReviewRequest;
import com.mygitgor.ecommerce_multivendor.controller.DTOs.response.ApiResponse;
import com.mygitgor.ecommerce_multivendor.domain.Product;
import com.mygitgor.ecommerce_multivendor.domain.Review;
import com.mygitgor.ecommerce_multivendor.domain.Users;
import com.mygitgor.ecommerce_multivendor.service.ProductService;
import com.mygitgor.ecommerce_multivendor.service.ReviewService;
import com.mygitgor.ecommerce_multivendor.service.UserService;
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
        Users users = userService.findByJwtToken(jwt);
        Product product = productService.findProductById(productId);
        Review review = reviewService.createReview(req, users,product);

        return ResponseEntity.ok(review);
    }

    @PatchMapping("/reviews/{reviewId}")
    public ResponseEntity<Review>updateReview(@RequestBody CreateReviewRequest req,
                                              @PathVariable Long reviewId,
                                              @RequestHeader("Authorization")
                                              String jwt) throws Exception
    {
        Users users = userService.findByJwtToken(jwt);
        Review review = reviewService.updateReview(
                reviewId, req.getReviewText(),req.getReviewRating(), users.getId()
        );
        return ResponseEntity.ok(review);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<ApiResponse>deleteReview(@PathVariable Long reviewId,
                                                   @RequestHeader("Authorization")
                                                   String jwt) throws Exception
    {
        Users users = userService.findByJwtToken(jwt);
        reviewService.deleteReview(reviewId, users.getId());

        ApiResponse response = new ApiResponse();
        response.setMessage("Review deleted successfully");

        return ResponseEntity.ok(response);
    }

}
