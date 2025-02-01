package com.mygitgor.ecommerce_multivendor.service;

import com.mygitgor.ecommerce_multivendor.domain.Product;
import com.mygitgor.ecommerce_multivendor.domain.Users;
import com.mygitgor.ecommerce_multivendor.domain.Wishlist;

public interface WishlistService {
    Wishlist createWishlist(Users users);
    Wishlist getWishlistByUserId(Users users);
    Wishlist addProductToWishlist(Users users, Product product);
}
