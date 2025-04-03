package com.mygitgor.ecommerce_multivendor.application.service.impl;

import com.mygitgor.ecommerce_multivendor.domain.model.CartItem;
import com.mygitgor.ecommerce_multivendor.domain.repository.CartItemRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.CartItemEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.UserEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.CartItemJpaRepository;
import com.mygitgor.ecommerce_multivendor.application.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws Exception {
        CartItem item = findCartItemById(id);

        if (item.getCart().getUser().getId().equals(userId)) {
            item.setQuantity(cartItem.getQuantity());
            item.setMrpPrice(item.getQuantity() * item.getProduct().getMrpPrice());
            item.setSellingPrice(item.getQuantity() * item.getProduct().getSellingPrice());
            return cartItemRepository.save(item);
        }
        throw new Exception("You can't update this cartItem");
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws Exception {
        CartItem item = findCartItemById(cartItemId);

        if (item.getCart().getUser().getId().equals(userId)) {
            cartItemRepository.delete(item);
        } else {
            throw new Exception("You can't delete this item");
        }
    }

    @Override
    public CartItem findCartItemById(Long id) throws Exception {
        return cartItemRepository.findById(id)
                .orElseThrow(() -> new Exception("Cart item not found with id " + id));
    }
}
