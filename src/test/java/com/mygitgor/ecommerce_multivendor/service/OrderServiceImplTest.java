package com.mygitgor.ecommerce_multivendor.service;

import com.mygitgor.ecommerce_multivendor.domain.*;
import com.mygitgor.ecommerce_multivendor.domain.costant.OrderStatus;
import com.mygitgor.ecommerce_multivendor.domain.costant.PaymentStatus;
import com.mygitgor.ecommerce_multivendor.repository.AddressRepository;
import com.mygitgor.ecommerce_multivendor.repository.OrderItemRepository;
import com.mygitgor.ecommerce_multivendor.repository.OrderRepository;
import com.mygitgor.ecommerce_multivendor.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class OrderServiceImplTest {

    @MockitoBean
    private OrderRepository orderRepository;

    @MockitoBean
    private AddressRepository addressRepository;

    @MockitoBean
    private OrderItemRepository orderItemRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private User user;
    private Address shippingAddress;
    private Cart cart;
    private Product product;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setAddresses(new HashSet<>());

        shippingAddress = new Address();
        shippingAddress.setId(1L);
        shippingAddress.setLocality("123 Test Street");

        product = new Product();
        product.setId(1L);

        Seller seller = new Seller();
        seller.setId(1L);
        product.setSeller(seller);

        CartItem cartItem = new CartItem();
        cartItem.setId(1L);
        cartItem.setProduct(product);
        cartItem.setQuantity(2);
        cartItem.setSellingPrice(100);

        cart = new Cart();
        cart.setCartItems(Collections.singletonList(cartItem));
    }

    @Test
    void createOrder_shouldCreateAndSaveOrders() {
        when(addressRepository.save(shippingAddress)).thenReturn(shippingAddress);
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(orderItemRepository.save(any(OrderItem.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Set<Order> orders = orderService.createOrder(user, shippingAddress, cart);

        assertNotNull(orders);
        assertEquals(1, orders.size());

        Order createdOrder = orders.iterator().next();
        assertEquals(OrderStatus.PENDING, createdOrder.getOrderStatus());
        assertEquals(PaymentStatus.PENDING, createdOrder.getPaymentDetails().getStatus());
        assertEquals(1, createdOrder.getOrderItems().size());

        verify(addressRepository, times(1)).save(shippingAddress);
        verify(orderRepository, times(1)).save(any(Order.class));
        verify(orderItemRepository, times(1)).save(any(OrderItem.class));
    }
}
