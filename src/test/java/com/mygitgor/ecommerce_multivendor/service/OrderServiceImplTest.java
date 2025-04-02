package com.mygitgor.ecommerce_multivendor.service;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.*;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.AddressJpaRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.OrderItemJpaRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.OrderJpaRepository;
import com.mygitgor.ecommerce_multivendor.application.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.*;

@SpringBootTest
@AutoConfigureMockMvc
class OrderServiceImplTest {

    @MockitoBean
    private OrderJpaRepository orderRepository;

    @MockitoBean
    private AddressJpaRepository addressRepository;

    @MockitoBean
    private OrderItemJpaRepository orderItemRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private UserEntity user;
    private AddressEntity shippingAddress;
    private CartEntity cart;
    private ProductEntity product;

    @BeforeEach
    void setUp() {
        user = new UserEntity();
        user.setId(1L);
        user.setAddresses(new HashSet<>());

        shippingAddress = new AddressEntity();
        shippingAddress.setId(1L);
        shippingAddress.setLocality("123 Test Street");

        product = new ProductEntity();
        product.setId(1L);

        SellerEntity seller = new SellerEntity();
        seller.setId(1L);
        product.setSeller(seller);

        CartItemEntity cartItem = new CartItemEntity();
        cartItem.setId(1L);
        cartItem.setProduct(product);
        cartItem.setQuantity(2);
        cartItem.setSellingPrice(100);

        cart = new CartEntity();
//        cart.setCartItems(Collections.singletonList(cartItem));
    }

//    @Test
//    void createOrder_shouldCreateAndSaveOrders() {
//        when(addressRepository.save(shippingAddress)).thenReturn(shippingAddress);
//        when(orderRepository.save(any(OrderEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));
//        when(orderItemRepository.save(any(OrderItemEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));
//
//        Set<OrderEntity> orders = orderService.createOrder(user, shippingAddress, cart);
//
//        assertNotNull(orders);
//        assertEquals(1, orders.size());
//
//        OrderEntity createdOrder = orders.iterator().next();
//        assertEquals(OrderStatus.PENDING, createdOrder.getOrderStatus());
//        assertEquals(PaymentStatus.PENDING, createdOrder.getPaymentDetails().getStatus());
//        assertEquals(1, createdOrder.getOrderItems().size());
//
//        verify(addressRepository, times(1)).save(shippingAddress);
//        verify(orderRepository, times(1)).save(any(OrderEntity.class));
//        verify(orderItemRepository, times(1)).save(any(OrderItemEntity.class));
//    }
}
