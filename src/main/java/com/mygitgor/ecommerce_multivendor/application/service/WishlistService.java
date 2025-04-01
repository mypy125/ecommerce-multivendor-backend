package com.mygitgor.ecommerce_multivendor.application.service;

import com.mygitgor.ecommerce_multivendor.domain.User;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.ProductEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.UserEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.WishlistEntity;

public interface WishlistService {
    WishlistEntity createWishlist(UserEntity user);
    WishlistEntity getWishlistByUserId(User user);
    WishlistEntity addProductToWishlist(User user, ProductEntity product);
}
