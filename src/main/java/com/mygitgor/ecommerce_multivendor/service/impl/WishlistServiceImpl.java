package com.mygitgor.ecommerce_multivendor.service.impl;

import com.mygitgor.ecommerce_multivendor.domain.Product;
import com.mygitgor.ecommerce_multivendor.domain.User;
import com.mygitgor.ecommerce_multivendor.domain.Wishlist;
import com.mygitgor.ecommerce_multivendor.repository.WishlistRepository;
import com.mygitgor.ecommerce_multivendor.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {
   private final WishlistRepository wishlistRepository;

    @Override
    public Wishlist createWishlist(User user) {
        return null;
    }

    @Override
    public Wishlist getWishlistByUserId(User user) {
        return null;
    }

    @Override
    public Wishlist addProductToWishlist(User user, Product product) {
        return null;
    }
}
