package com.mygitgor.ecommerce_multivendor.service;

import com.mygitgor.ecommerce_multivendor.domain.Cart;
import com.mygitgor.ecommerce_multivendor.domain.CartItem;
import com.mygitgor.ecommerce_multivendor.domain.Product;
import com.mygitgor.ecommerce_multivendor.domain.Users;

public interface CartService {
    CartItem addCartItem(Users users,
                         Product product,
                         String size,
                         int quantity) throws IllegalAccessException;
    Cart findUserCart(Users users) throws IllegalAccessException;
}
