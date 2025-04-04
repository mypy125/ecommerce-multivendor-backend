package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.model.Wishlist;
import com.mygitgor.ecommerce_multivendor.domain.repository.WishlistRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.WishlistEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.WishlistJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.WishlistMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class WishlistRepositoryImpl implements WishlistRepository {
    private final WishlistJpaRepository jpaRepository;
    private final WishlistMapper wishlistMapper;

    @Override
    public Wishlist save(Wishlist wishlist) {
        WishlistEntity entity = wishlistMapper.toEntity(wishlist);
        return wishlistMapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<Wishlist> findByUserId(Long userId) {
        return jpaRepository.findByUserId(userId)
                .map(wishlistMapper::toDomain);
    }
}
