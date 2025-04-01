package com.mygitgor.ecommerce_multivendor.application.service.impl;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.Product;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.UserEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.Wishlist;
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
    public Wishlist createWishlist(UserEntity user) {
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        return wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist getWishlistByUserId(UserEntity user) {
        Optional<Wishlist> wishlist = wishlistRepository.findByUserId(user.getId());
        return wishlist.orElseGet(() -> createWishlist(user));
    }

    @Override
    public Wishlist addProductToWishlist(UserEntity user, Product product) {
        Wishlist wishlist = getWishlistByUserId(user);
        if(wishlist.getProducts().contains(product)){
            wishlist.getProducts().remove(product);
        }
        else wishlist.getProducts().add(product);
        return wishlistRepository.save(wishlist);
    }
}
