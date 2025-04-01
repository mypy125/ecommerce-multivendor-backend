package com.mygitgor.ecommerce_multivendor.application.service.impl;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.CartItemEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.UserEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.CartItemJpaRepository;
import com.mygitgor.ecommerce_multivendor.application.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemJpaRepository cartItemRepository;

    @Override
    public CartItemEntity updateCartItem(Long userId, Long id, CartItemEntity cartItem) throws Exception {
        CartItemEntity item = findCartItemById(id);

        UserEntity cartItemUser = item.getCart().getUser();
        if(cartItemUser.getId().equals(userId)){
            item.setQuantity(cartItem.getQuantity());
            item.setMrpPrice(item.getQuantity()*item.getProduct().getMrpPrice());
            item.setSellingPrice(item.getQuantity()*item.getProduct().getSellingPrice());
            return cartItemRepository.save(item);
        }
        throw new Exception("you can't update this cartItem");
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws Exception {
        CartItemEntity item = findCartItemById(cartItemId);

        UserEntity cartItemUser = item.getCart().getUser();
        if(cartItemUser.getId().equals(userId)){
            cartItemRepository.delete(item);
        }
        else throw new Exception("you can't delete this item");
    }

    @Override
    public CartItemEntity findCartItemById(Long id) throws Exception {
        return cartItemRepository.findById(id)
                .orElseThrow(()-> new Exception("cart item not found with id "+id));
    }
}
