package com.mygitgor.ecommerce_multivendor.application.service;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.Cart;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.CartItem;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.Product;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.UserEntity;

public interface CartService {
    CartItem addCartItem(UserEntity user,
                         Product product,
                         String size,
                         int quantity) throws IllegalAccessException;
    Cart findUserCart(UserEntity user) throws IllegalAccessException;
}
