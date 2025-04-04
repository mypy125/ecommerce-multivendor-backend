package com.mygitgor.ecommerce_multivendor.api.controller;

import com.mygitgor.ecommerce_multivendor.domain.model.Product;
import com.mygitgor.ecommerce_multivendor.domain.model.User;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.ProductEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.WishlistEntity;
import com.mygitgor.ecommerce_multivendor.application.service.ProductService;
import com.mygitgor.ecommerce_multivendor.application.service.UserService;
import com.mygitgor.ecommerce_multivendor.application.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wishlist")
public class WishlistController {
    private final WishlistService wishlistService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<WishlistEntity>getWishlistByUserId(@RequestHeader("Authorization")
                                                           String jwt) throws Exception
    {
        User user = userService.findByJwtToken(jwt);
        WishlistEntity wishlist = wishlistService.getWishlistByUserId(user);
        return ResponseEntity.ok(wishlist);
    }

    @PostMapping("/add-product/{productId}")
    public ResponseEntity<WishlistEntity>addProductToWishlist(@PathVariable Long productId,
                                                              @RequestHeader("Authorization")
                                                           String jwt) throws Exception
    {
        Product product = productService.findProductById(productId);
        User user = userService.findByJwtToken(jwt);
        WishlistEntity updateWishlist = wishlistService.addProductToWishlist(user,product);
        return ResponseEntity.ok(updateWishlist);
    }
}
