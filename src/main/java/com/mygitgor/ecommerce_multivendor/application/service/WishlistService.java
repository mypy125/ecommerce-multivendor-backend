package com.mygitgor.ecommerce_multivendor.application.service;

import com.mygitgor.ecommerce_multivendor.domain.model.Product;
import com.mygitgor.ecommerce_multivendor.domain.model.User;
import com.mygitgor.ecommerce_multivendor.domain.model.Wishlist;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.ProductEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.UserEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.WishlistEntity;

public interface WishlistService {
    Wishlist createWishlist(User user);
    Wishlist getWishlistByUserId(User user);
    Wishlist addProductToWishlist(User user, Product product);
}
