package com.mygitgor.ecommerce_multivendor.application.service;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.Product;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.UserEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.Wishlist;

public interface WishlistService {
    Wishlist createWishlist(UserEntity user);
    Wishlist getWishlistByUserId(UserEntity user);
    Wishlist addProductToWishlist(UserEntity user, Product product);
}
