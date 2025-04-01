package com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.CartEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.CartItemEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemJpaRepository extends JpaRepository<CartItemEntity, Long> {
    CartItemEntity findByCartAndProductAndSize(CartEntity cart, ProductEntity product, String size);
}
