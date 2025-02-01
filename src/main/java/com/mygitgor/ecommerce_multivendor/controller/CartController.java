package com.mygitgor.ecommerce_multivendor.controller;

import com.mygitgor.ecommerce_multivendor.controller.DTOs.request.AddItemRequest;
import com.mygitgor.ecommerce_multivendor.controller.DTOs.response.ApiResponse;
import com.mygitgor.ecommerce_multivendor.domain.Cart;
import com.mygitgor.ecommerce_multivendor.domain.CartItem;
import com.mygitgor.ecommerce_multivendor.domain.Product;
import com.mygitgor.ecommerce_multivendor.domain.Users;
import com.mygitgor.ecommerce_multivendor.service.CartItemService;
import com.mygitgor.ecommerce_multivendor.service.CartService;
import com.mygitgor.ecommerce_multivendor.service.ProductService;
import com.mygitgor.ecommerce_multivendor.service.UserService;
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
    public ResponseEntity<Cart>findUserCartHandler(@RequestHeader("Authorization")
                                                       String jwt) throws Exception
    {
        Users users =userService.findByJwtToken(jwt);
        Cart cart = cartService.findUserCart(users);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<ApiResponse>addItemToCart(@RequestBody AddItemRequest request,
                                                 @RequestHeader("Authorization")
                                                       String jwt) throws Exception
    {
        Users users =userService.findByJwtToken(jwt);
        Product product = productService.findProductById(request.getProductId());

        CartItem item = cartService.addCartItem(
                users,product,request.getSize(),request.getQuantity()
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
        Users users =userService.findByJwtToken(jwt);
        cartItemService.removeCartItem(users.getId(), cartItemId);
        ApiResponse response = new ApiResponse();
        response.setMessage("Item remove from cart Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("item/{cartItemId}")
    public ResponseEntity<CartItem>updateCartItemHandler(@PathVariable Long cartItemId,
                                                            @RequestBody CartItem cartItem,
                                                            @RequestHeader("Authorization")
                                                            String jwt) throws Exception
    {
        Users users =userService.findByJwtToken(jwt);
       CartItem updateCartItem = null;
       if(cartItem.getQuantity()>0){
           updateCartItem=cartItemService.updateCartItem(users.getId(), cartItemId, cartItem);
       }

        return new ResponseEntity<>(updateCartItem, HttpStatus.ACCEPTED);
    }


}
