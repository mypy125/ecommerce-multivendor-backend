package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.repository.WishlistRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.WishlistJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.WishlistMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WishlistRepositoryImpl implements WishlistRepository {
    private final WishlistJpaRepository jpaRepository;
    private final WishlistMapper wishlistMapper;
}
