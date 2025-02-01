package com.mygitgor.ecommerce_multivendor.service.impl;

import com.mygitgor.ecommerce_multivendor.domain.Product;
import com.mygitgor.ecommerce_multivendor.domain.Users;
import com.mygitgor.ecommerce_multivendor.domain.Wishlist;
import com.mygitgor.ecommerce_multivendor.repository.WishlistRepository;
import com.mygitgor.ecommerce_multivendor.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {
   private final WishlistRepository wishlistRepository;

    @Override
    public Wishlist createWishlist(Users users) {
        Wishlist wishlist = new Wishlist();
        wishlist.setUsers(users);
        return wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist getWishlistByUserId(Users users) {
        Wishlist wishlist = wishlistRepository.findByUsersId(users.getId());
        if(wishlist==null){
            wishlist=createWishlist(users);
        }
        return wishlist;
    }

    @Override
    public Wishlist addProductToWishlist(Users users, Product product) {
        Wishlist wishlist = getWishlistByUserId(users);
        if(wishlist.getProducts().contains(product)){
            wishlist.getProducts().remove(product);
        }
        else wishlist.getProducts().add(product);
        return wishlistRepository.save(wishlist);
    }
}
