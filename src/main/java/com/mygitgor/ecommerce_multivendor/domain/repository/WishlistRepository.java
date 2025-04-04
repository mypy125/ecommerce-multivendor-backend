package com.mygitgor.ecommerce_multivendor.domain.repository;

import com.mygitgor.ecommerce_multivendor.domain.model.Wishlist;

import java.util.Optional;

public interface WishlistRepository {
    Wishlist save(Wishlist wishlist);
    Optional<Wishlist> findByUserId(Long userId);
}
