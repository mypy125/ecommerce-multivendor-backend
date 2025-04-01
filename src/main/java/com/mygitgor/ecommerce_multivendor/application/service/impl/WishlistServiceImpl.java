package com.mygitgor.ecommerce_multivendor.application.service.impl;

import com.mygitgor.ecommerce_multivendor.domain.User;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.ProductEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.UserEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.WishlistEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.WishlistJpaRepository;
import com.mygitgor.ecommerce_multivendor.application.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {
   private final WishlistJpaRepository wishlistRepository;

    @Override
    public WishlistEntity createWishlist(UserEntity user) {
        WishlistEntity wishlist = new WishlistEntity();
        wishlist.setUser(user);
        return wishlistRepository.save(wishlist);
    }

    @Override
    public WishlistEntity getWishlistByUserId(User user) {
        Optional<WishlistEntity> wishlist = wishlistRepository.findByUserId(user.getId());
        return wishlist.orElseGet(() -> createWishlist(user));
    }

    @Override
    public WishlistEntity addProductToWishlist(User user, ProductEntity product) {
        WishlistEntity wishlist = getWishlistByUserId(user);
        if(wishlist.getProducts().contains(product)){
            wishlist.getProducts().remove(product);
        }
        else wishlist.getProducts().add(product);
        return wishlistRepository.save(wishlist);
    }
}
