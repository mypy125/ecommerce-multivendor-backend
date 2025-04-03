package com.mygitgor.ecommerce_multivendor.domain.repository;

import com.mygitgor.ecommerce_multivendor.domain.model.Cart;

import java.util.Optional;

public interface CartRepository {
    Optional<Cart> findByUserId(Long userId);
    Cart save(Cart cart);
}
