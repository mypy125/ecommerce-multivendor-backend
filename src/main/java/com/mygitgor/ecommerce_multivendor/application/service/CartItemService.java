package com.mygitgor.ecommerce_multivendor.application.service;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.CartItemEntity;

public interface CartItemService {
    CartItemEntity updateCartItem(Long userId, Long id, CartItemEntity cartItem) throws Exception;
    void removeCartItem(Long userId, Long cartItemId) throws Exception;
    CartItemEntity findCartItemById(Long id) throws Exception;
}
