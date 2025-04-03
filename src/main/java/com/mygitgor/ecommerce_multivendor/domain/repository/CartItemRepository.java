package com.mygitgor.ecommerce_multivendor.domain.repository;

import com.mygitgor.ecommerce_multivendor.domain.model.Cart;
import com.mygitgor.ecommerce_multivendor.domain.model.CartItem;
import com.mygitgor.ecommerce_multivendor.domain.model.Product;

import java.util.Optional;

public interface CartItemRepository {
    CartItem save(CartItem cartItem);
    void delete(CartItem cartItem);
    Optional<CartItem> findById(Long id);
    Optional<CartItem> findByCartAndProductAndSize(Cart cart, Product product, String size);
}
