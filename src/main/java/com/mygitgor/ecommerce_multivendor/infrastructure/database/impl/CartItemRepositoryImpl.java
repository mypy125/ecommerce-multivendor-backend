package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.model.Cart;
import com.mygitgor.ecommerce_multivendor.domain.model.CartItem;
import com.mygitgor.ecommerce_multivendor.domain.model.Product;
import com.mygitgor.ecommerce_multivendor.domain.repository.CartItemRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.CartEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.CartItemEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.ProductEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.CartItemJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.CartItemMapper;
import com.mygitgor.ecommerce_multivendor.mapper.CartMapper;
import com.mygitgor.ecommerce_multivendor.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CartItemRepositoryImpl implements CartItemRepository {
    private final CartItemJpaRepository jpaRepository;
    private final CartItemMapper cartItemMapper;
    private final CartMapper cartMapper;
    private final ProductMapper productMapper;

    @Override
    public CartItem save(CartItem cartItem) {
        CartItemEntity entity = cartItemMapper.toEntity(cartItem);
        return cartItemMapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public void delete(CartItem cartItem) {
        jpaRepository.delete(cartItemMapper.toEntity(cartItem));
    }

    @Override
    public Optional<CartItem> findById(Long id) {
        return jpaRepository.findById(id).map(cartItemMapper::toDomain);
    }

    @Override
    public Optional<CartItem> findByCartAndProductAndSize(Cart cart, Product product, String size) {
        CartEntity cartEntity = cartMapper.toEntity(cart);
        ProductEntity productEntity = productMapper.toEntity(product);

        return jpaRepository.findByCartAndProductAndSize(cartEntity, productEntity, size)
                .map(cartItemMapper::toDomain);
    }
}
