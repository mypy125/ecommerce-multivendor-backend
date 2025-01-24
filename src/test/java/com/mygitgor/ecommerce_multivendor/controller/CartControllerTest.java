package com.mygitgor.ecommerce_multivendor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mygitgor.ecommerce_multivendor.controller.DTOs.request.AddItemRequest;
import com.mygitgor.ecommerce_multivendor.controller.DTOs.response.ApiResponse;
import com.mygitgor.ecommerce_multivendor.domain.Cart;
import com.mygitgor.ecommerce_multivendor.domain.CartItem;
import com.mygitgor.ecommerce_multivendor.domain.Product;
import com.mygitgor.ecommerce_multivendor.domain.User;
import com.mygitgor.ecommerce_multivendor.service.CartItemService;
import com.mygitgor.ecommerce_multivendor.service.CartService;
import com.mygitgor.ecommerce_multivendor.service.ProductService;
import com.mygitgor.ecommerce_multivendor.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private CartService cartService;

    @Mock
    private CartItemService cartItemService;

    @Mock
    private UserService userService;

    @Mock
    private ProductService productService;

    @InjectMocks
    private CartController cartController;

    private User user;
    private Product product;
    private CartItem cartItem;
    private AddItemRequest addItemRequest;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();

        user = new User();
        user.setId(1L);
        user.setFullName("Test User");

        product = new Product();
        product.setId(1L);
        product.setTitle("Test Product");

        cartItem = new CartItem();
        cartItem.setId(1L);
        cartItem.setProduct(product);
        cartItem.setQuantity(2);

        addItemRequest = new AddItemRequest();
        addItemRequest.setProductId(1L);
        addItemRequest.setQuantity(2);
        addItemRequest.setSize("M");
    }

    @Test
    void addItemToCart_shouldReturnSuccess() throws Exception {
        when(userService.findByJwtToken("valid-jwt")).thenReturn(user);
        when(productService.findProductById(1L)).thenReturn(product);
        when(cartService.addCartItem(any(), any(), any(), anyInt())).thenReturn(cartItem);

        mockMvc.perform(put("/api/cart/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addItemRequest))
                        .header("Authorization", "valid-jwt"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Item added to cart Successfully"));
    }

    @Test
    void deleteCartItem_shouldReturnSuccess() throws Exception {
        when(userService.findByJwtToken("valid-jwt")).thenReturn(user);
        doNothing().when(cartItemService).removeCartItem(user.getId(), 1L);

        mockMvc.perform(delete("/api/cart/item/1")
                        .header("Authorization", "valid-jwt"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Item remove from cart Successfully"));
    }

    @Test
    void updateCartItem_shouldReturnUpdatedCartItem() throws Exception {
        CartItem updatedCartItem = new CartItem();
        updatedCartItem.setId(1L);
        updatedCartItem.setProduct(product);
        updatedCartItem.setQuantity(3);

        when(userService.findByJwtToken("valid-jwt")).thenReturn(user);
        when(cartItemService.updateCartItem(user.getId(), 1L, cartItem)).thenReturn(updatedCartItem);

        mockMvc.perform(put("/api/cart/item/1")
                        .header("Authorization", "valid-jwt")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(cartItem)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.quantity").value(3));
    }

    @Test
    void getUserCart_shouldReturnCart() throws Exception {
        Cart cart = new Cart();
        cart.setId(1L);

        when(userService.findByJwtToken("valid-jwt")).thenReturn(user);
        when(cartService.findUserCart(user)).thenReturn(cart);

        mockMvc.perform(get("/api/cart")
                        .header("Authorization", "valid-jwt"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }
}

