package com.mygitgor.ecommerce_multivendor.application.service;

import com.mygitgor.ecommerce_multivendor.domain.model.Cart;
import com.mygitgor.ecommerce_multivendor.domain.model.CartItem;
import com.mygitgor.ecommerce_multivendor.domain.model.Product;
import com.mygitgor.ecommerce_multivendor.domain.model.User;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.CartEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.CartItemEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.ProductEntity;

public interface CartService {
    CartItem addCartItem(User user,
                         Product product,
                         String size,
                         int quantity) throws IllegalAccessException;
    Cart findUserCart(User user) throws IllegalAccessException;
}
