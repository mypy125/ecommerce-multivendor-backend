package com.mygitgor.ecommerce_multivendor.service.impl;

import com.mygitgor.ecommerce_multivendor.domain.Cart;
import com.mygitgor.ecommerce_multivendor.domain.CartItem;
import com.mygitgor.ecommerce_multivendor.domain.Product;
import com.mygitgor.ecommerce_multivendor.domain.Users;
import com.mygitgor.ecommerce_multivendor.repository.CartItemRepository;
import com.mygitgor.ecommerce_multivendor.repository.CartRepository;
import com.mygitgor.ecommerce_multivendor.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public CartItem addCartItem(Users users, Product product, String size, int quantity) throws IllegalAccessException {
        Cart cart = findUserCart(users);

        CartItem isPresent = cartItemRepository.findByCartAndProductAndSize(cart,product,size);
        if(isPresent==null){
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUserId(users.getId());
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
    public Cart findUserCart(Users users) throws IllegalAccessException {
        Cart cart = cartRepository.findByUsersId(users.getId());

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
