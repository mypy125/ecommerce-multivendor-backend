package com.mygitgor.ecommerce_multivendor.api.controller;

import com.mygitgor.ecommerce_multivendor.api.DTOs.request.AddItemRequest;
import com.mygitgor.ecommerce_multivendor.api.DTOs.response.ApiResponse;
import com.mygitgor.ecommerce_multivendor.domain.model.User;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.CartEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.CartItemEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.ProductEntity;
import com.mygitgor.ecommerce_multivendor.application.service.CartItemService;
import com.mygitgor.ecommerce_multivendor.application.service.CartService;
import com.mygitgor.ecommerce_multivendor.application.service.ProductService;
import com.mygitgor.ecommerce_multivendor.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;
    private final CartItemService cartItemService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<CartEntity>findUserCartHandler(@RequestHeader("Authorization")
                                                       String jwt) throws Exception
    {
        User user =userService.findByJwtToken(jwt);
        CartEntity cart = cartService.findUserCart(user);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<ApiResponse>addItemToCart(@RequestBody AddItemRequest request,
                                                 @RequestHeader("Authorization")
                                                       String jwt) throws Exception
    {
        User user =userService.findByJwtToken(jwt);
        ProductEntity product = productService.findProductById(request.getProductId());

        CartItemEntity item = cartService.addCartItem(
                user,product,request.getSize(),request.getQuantity()
        );
        ApiResponse response = new ApiResponse();
        response.setMessage("Item added to cart Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("item/{cartItemId}")
    public ResponseEntity<ApiResponse>deleteCartItemHandler(@PathVariable Long cartItemId,
                                                            @RequestHeader("Authorization")
                                                            String jwt) throws Exception
    {
        User user =userService.findByJwtToken(jwt);
        cartItemService.removeCartItem(user.getId(), cartItemId);
        ApiResponse response = new ApiResponse();
        response.setMessage("Item remove from cart Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("item/{cartItemId}")
    public ResponseEntity<CartItemEntity>updateCartItemHandler(@PathVariable Long cartItemId,
                                                               @RequestBody CartItemEntity cartItem,
                                                               @RequestHeader("Authorization")
                                                            String jwt) throws Exception
    {
        User user =userService.findByJwtToken(jwt);
       CartItemEntity updateCartItem = null;
       if(cartItem.getQuantity()>0){
           updateCartItem=cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);
       }

        return new ResponseEntity<>(updateCartItem, HttpStatus.ACCEPTED);
    }


}
