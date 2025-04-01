package com.mygitgor.ecommerce_multivendor.application.service.impl;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.Cart;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.CartItem;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.Product;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.UserEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.CartItemJpaRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.CartJpaRepository;
import com.mygitgor.ecommerce_multivendor.application.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartJpaRepository cartRepository;
    private final CartItemJpaRepository cartItemRepository;

    @Override
    public CartItem addCartItem(UserEntity user, Product product, String size, int quantity) throws IllegalAccessException {
        Cart cart = findUserCart(user);

        CartItem isPresent = cartItemRepository.findByCartAndProductAndSize(cart,product,size);
        if(isPresent==null){
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUserId(user.getId());
            cartItem.setSize(size);

            int totalPrice=quantity*product.getSellingPrice();
            cartItem.setSellingPrice(totalPrice);
            cartItem.setMrpPrice(quantity*product.getMrpPrice());

            cart.getCartItems().add(cartItem);
            cartItem.setCart(cart);

            return cartItemRepository.save(cartItem);
        }
        return isPresent;
    }

    @Override
    public Cart findUserCart(UserEntity user) throws IllegalAccessException {
        Cart cart = cartRepository.findByUserId(user.getId());

        int totalPrice=0;
        int totalDiscountedPrice=0;
        int totalItem=0;

        for(CartItem cartItem: cart.getCartItems()){
            totalPrice += cartItem.getMrpPrice();
            totalDiscountedPrice += cartItem.getSellingPrice();
            totalItem += cartItem.getQuantity();
        }
        cart.setTotalMrpPrice(totalPrice);
        cart.setTotalItem(totalItem);
        cart.setTotalSellingPrice(totalDiscountedPrice);
        cart.setDiscount(calculateDiscountPercentage(totalPrice,totalDiscountedPrice));
        cart.setTotalItem(totalItem);
        return cart;
    }

    private int calculateDiscountPercentage(int mrpPrice, int sellingPrice) throws IllegalAccessException {
        if(mrpPrice < 0){
//            throw new IllegalAccessException("Actual price must be greater than 0");
            return 0;
        }
        double discount = mrpPrice - sellingPrice;
        double discountPercentage= (discount/mrpPrice)*100;
        return (int) discountPercentage;
    }
}
