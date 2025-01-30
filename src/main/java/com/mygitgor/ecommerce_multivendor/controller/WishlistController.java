package com.mygitgor.ecommerce_multivendor.controller;

import com.mygitgor.ecommerce_multivendor.domain.Product;
import com.mygitgor.ecommerce_multivendor.domain.User;
import com.mygitgor.ecommerce_multivendor.domain.Wishlist;
import com.mygitgor.ecommerce_multivendor.service.ProductService;
import com.mygitgor.ecommerce_multivendor.service.UserService;
import com.mygitgor.ecommerce_multivendor.service.WishlistService;
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
    public ResponseEntity<Wishlist>getWishlistByUserId(@RequestHeader("Authorization")
                                                           String jwt) throws Exception
    {
        User user = userService.findByJwtToken(jwt);
        Wishlist wishlist = wishlistService.getWishlistByUserId(user);
        return ResponseEntity.ok(wishlist);
    }

    @PostMapping("/add-product/{productId}")
    public ResponseEntity<Wishlist>addProductToWishlist(@PathVariable Long productId,
                                                        @RequestHeader("Authorization")
                                                           String jwt) throws Exception
    {
        Product product = productService.findProductById(productId);
        User user = userService.findByJwtToken(jwt);
        Wishlist updateWishlist = wishlistService.addProductToWishlist(user,product);
        return ResponseEntity.ok(updateWishlist);
    }
}
