package com.mygitgor.ecommerce_multivendor.application.service;

import com.mygitgor.ecommerce_multivendor.domain.model.User;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.ProductEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.UserEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.WishlistEntity;

public interface WishlistService {
    WishlistEntity createWishlist(UserEntity user);
    WishlistEntity getWishlistByUserId(User user);
    WishlistEntity addProductToWishlist(User user, ProductEntity product);
}
