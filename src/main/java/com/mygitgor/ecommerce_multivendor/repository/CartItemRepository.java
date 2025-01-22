package com.mygitgor.ecommerce_multivendor.repository;

import com.mygitgor.ecommerce_multivendor.domain.Cart;
import com.mygitgor.ecommerce_multivendor.domain.CartItem;
import com.mygitgor.ecommerce_multivendor.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);
}
