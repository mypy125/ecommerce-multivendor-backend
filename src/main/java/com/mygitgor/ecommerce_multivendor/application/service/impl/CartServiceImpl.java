package com.mygitgor.ecommerce_multivendor.application.service.impl;

import com.mygitgor.ecommerce_multivendor.domain.model.Cart;
import com.mygitgor.ecommerce_multivendor.domain.model.CartItem;
import com.mygitgor.ecommerce_multivendor.domain.model.Product;
import com.mygitgor.ecommerce_multivendor.domain.model.User;
import com.mygitgor.ecommerce_multivendor.domain.repository.CartItemRepository;
import com.mygitgor.ecommerce_multivendor.domain.repository.CartRepository;
import com.mygitgor.ecommerce_multivendor.application.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public CartItem addCartItem(User user, Product product, String size, int quantity) {
        Cart cart = findOrCreateUserCart(user);

        CartItem cartItem = cartItemRepository.findByCartAndProductAndSize(cart, product, size)
                .orElseGet(() -> createNewCartItem(cart, user, product, size, quantity));

        updateCartTotals(cart);
        return cartItem;
    }

    private CartItem createNewCartItem(Cart cart,
                                       User user,
                                       Product product,
                                       String size,
                                       int quantity
    ) {
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setUserId(user.getId());
        cartItem.setSize(size);
        cartItem.setSellingPrice(quantity * product.getSellingPrice());
        cartItem.setMrpPrice(quantity * product.getMrpPrice());

        cart.getCartItems().add(cartItem);
        cartItem.setCart(cart);

        return cartItemRepository.save(cartItem);
    }

    @Override
    public Cart findUserCart(User user) {
        return findOrCreateUserCart(user);
    }

    private Cart findOrCreateUserCart(User user) {
        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseGet(() -> createNewCart(user));

        updateCartTotals(cart);
        return cart;
    }

    private Cart createNewCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setCartItems(new HashSet<>());
        return cartRepository.save(cart);
    }

    private void updateCartTotals(Cart cart) {
        int totalPrice = cart.getCartItems().stream().mapToInt(CartItem::getMrpPrice).sum();
        int totalDiscountedPrice = cart.getCartItems().stream().mapToInt(CartItem::getSellingPrice).sum();
        int totalItems = cart.getCartItems().stream().mapToInt(CartItem::getQuantity).sum();

        cart.setTotalMrpPrice(totalPrice);
        cart.setTotalSellingPrice(totalDiscountedPrice);
        cart.setTotalItem(totalItems);
        cart.setDiscount(calculateDiscountPercentage(totalPrice, totalDiscountedPrice));
    }

    private int calculateDiscountPercentage(int mrpPrice, int sellingPrice) {
        return (mrpPrice > 0) ? (int) (((double) (mrpPrice - sellingPrice) / mrpPrice) * 100) : 0;
    }
}
