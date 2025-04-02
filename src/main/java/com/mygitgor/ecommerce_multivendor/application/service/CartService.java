package com.mygitgor.ecommerce_multivendor.application.service;

import com.mygitgor.ecommerce_multivendor.domain.model.User;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.CartEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.CartItemEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.ProductEntity;

public interface CartService {
    CartItemEntity addCartItem(User user,
                               ProductEntity product,
                               String size,
                               int quantity) throws IllegalAccessException;
    CartEntity findUserCart(User user) throws IllegalAccessException;
}
