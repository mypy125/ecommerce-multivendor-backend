package com.mygitgor.ecommerce_multivendor.application.impl;

import com.mygitgor.ecommerce_multivendor.domain.model.*;
import com.mygitgor.ecommerce_multivendor.domain.model.costant.OrderStatus;
import com.mygitgor.ecommerce_multivendor.domain.model.costant.PaymentStatus;
import com.mygitgor.ecommerce_multivendor.domain.model.details.PaymentDetails;
import com.mygitgor.ecommerce_multivendor.domain.repository.AddressRepository;
import com.mygitgor.ecommerce_multivendor.domain.repository.OrderItemRepository;
import com.mygitgor.ecommerce_multivendor.domain.repository.OrderRepository;
import com.mygitgor.ecommerce_multivendor.application.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public Set<Order> createOrder(User user, Address shippingAddress, Cart cart) {
        if (user.getAddresses().add(shippingAddress)) {
            addressRepository.save(shippingAddress);
        }

        Map<Long, List<CartItem>> itemsBySeller = cart.getCartItems().stream()
                .collect(Collectors.groupingBy(item -> item.getProduct().getSeller().getId()));

        return itemsBySeller.entrySet().stream()
                .map(entry -> createOrderForSeller(user, shippingAddress, entry.getKey(), entry.getValue()))
                .collect(Collectors.toSet());
    }

    private Order createOrderForSeller(User user, Address shippingAddress, Long sellerId, List<CartItem> items) {
        int totalOrderPrice = items.stream().mapToInt(CartItem::getSellingPrice).sum();
        int totalItems = items.stream().mapToInt(CartItem::getQuantity).sum();

        Order order = Order.builder()
                .user(user)
                .sellerId(sellerId)
                .totalMrpPrice(totalOrderPrice)
                .totalSellingPrice(totalOrderPrice)
                .totalItem(totalItems)
                .shippingAddress(shippingAddress)
                .orderStatus(OrderStatus.PENDING)
                .paymentDetails(new PaymentDetails(PaymentStatus.PENDING))
                .build();

        Order savedOrder = orderRepository.save(order);
        List<OrderItem> orderItems = items.stream()
                .map(item -> createOrderItem(savedOrder, item))
                .toList();

        savedOrder.getOrderItems().addAll(orderItems);
        return savedOrder;
    }

    private OrderItem createOrderItem(Order order, CartItem item) {
        return orderItemRepository.save(OrderItem.builder()
                .order(order)
                .mrpPrice(item.getMrpPrice())
                .product(item.getProduct())
                .quantity(item.getQuantity())
                .size(item.getSize())
                .userId(item.getUserId())
                .sellingPrice(item.getSellingPrice())
                .build());
    }

    @Override
    public Order findOrderById(Long id) throws Exception {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> usersOrderHistory(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<Order> sellersOrder(Long sellerId) {
        return orderRepository.findBySellerId(sellerId);
    }

    @Override
    public Order updateOrderStatus(Long orderId, OrderStatus status) throws Exception {
        Order order = findOrderById(orderId);
        order.setOrderStatus(status);
        return orderRepository.save(order);
    }

    @Override
    public Order cancelOrder(Long orderId, User user) throws Exception {
        Order order = findOrderById(orderId);

        if (!user.getId().equals(order.getUser().getId())) {
            throw new Exception("You don't have access to this order!");
        }

        order.setOrderStatus(OrderStatus.CANCELED);
        return orderRepository.save(order);
    }

    @Override
    public OrderItem getOrderItemById(Long id) {
        return orderItemRepository.findById(id);
    }
}