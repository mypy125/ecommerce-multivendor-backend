package com.mygitgor.ecommerce_multivendor.service;

import com.mygitgor.ecommerce_multivendor.domain.Product;
import com.mygitgor.ecommerce_multivendor.domain.User;
import com.mygitgor.ecommerce_multivendor.domain.Wishlist;

public interface WishlistService {
    Wishlist createWishlist(User user);
    Wishlist getWishlistByUserId(User user);
    Wishlist addProductToWishlist(User user, Product product);
}
