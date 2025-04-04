package com.mygitgor.ecommerce_multivendor.application.impl;

import com.mygitgor.ecommerce_multivendor.domain.model.Product;
import com.mygitgor.ecommerce_multivendor.domain.model.User;
import com.mygitgor.ecommerce_multivendor.domain.model.Wishlist;
import com.mygitgor.ecommerce_multivendor.domain.repository.WishlistRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.ProductEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.UserEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.WishlistEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.WishlistJpaRepository;
import com.mygitgor.ecommerce_multivendor.application.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {
   private final WishlistRepository wishlistRepository;

    @Override
    public Wishlist createWishlist(User user) {
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        return wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist getWishlistByUserId(User user) {
        return wishlistRepository.findByUserId(user.getId())
                .orElseGet(() -> createWishlist(user));
    }

    @Override
    public Wishlist addProductToWishlist(User user, Product product) {
        Wishlist wishlist = getWishlistByUserId(user);
        if (wishlist.getProducts().contains(product)) {
            wishlist.getProducts().remove(product);
        } else {
            wishlist.getProducts().add(product);
        }
        return wishlistRepository.save(wishlist);
    }
}
