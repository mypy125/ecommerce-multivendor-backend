package com.mygitgor.ecommerce_multivendor.application.service;

import com.mygitgor.ecommerce_multivendor.domain.User;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.CartEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.CartItemEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.ProductEntity;

public interface CartService {
    CartItemEntity addCartItem(User user,
                               ProductEntity product,
                               String size,
                               int quantity) throws IllegalAccessException;
    CartEntity findUserCart(User user) throws IllegalAccessException;
}
