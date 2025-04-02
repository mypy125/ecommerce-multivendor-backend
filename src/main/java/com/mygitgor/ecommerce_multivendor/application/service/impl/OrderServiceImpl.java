package com.mygitgor.ecommerce_multivendor.application.service.impl;

import com.mygitgor.ecommerce_multivendor.domain.model.User;
import com.mygitgor.ecommerce_multivendor.domain.model.costant.OrderStatus;
import com.mygitgor.ecommerce_multivendor.domain.model.costant.PaymentStatus;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.*;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.AddressJpaRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.OrderItemJpaRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.OrderJpaRepository;
import com.mygitgor.ecommerce_multivendor.application.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderJpaRepository orderRepository;
    private final AddressJpaRepository addressRepository;
    private final OrderItemJpaRepository orderItemRepository;

    @Override
    public Set<OrderEntity> createOrder(User user,
                                        AddressEntity shippingAddress,
                                        CartEntity cart
    ) {
        if(!user.getAddresses().contains(shippingAddress)){
            user.getAddresses().add(shippingAddress);
        }
        AddressEntity address = addressRepository.save(shippingAddress);

        Map<Long, List<CartItemEntity>>itemsBySeller=cart.getCartItems().stream()
                .collect(Collectors.groupingBy(item ->
                        item.getProduct().getSeller().getId()));

        Set<OrderEntity>orders = new HashSet<>();
        for(Map.Entry<Long, List<CartItemEntity>> entry : itemsBySeller.entrySet()){
            Long sellerId= entry.getKey();
            List<CartItemEntity>items = entry.getValue();

            int totalOrderPrice = items.stream().mapToInt(
                    CartItemEntity::getSellingPrice
            ).sum();

            int totalItem = items.stream().mapToInt(CartItemEntity::getQuantity).sum();

            OrderEntity createOrder = new OrderEntity();
            createOrder.setUser(user);
            createOrder.setSellerId(sellerId);
            createOrder.setTotalMrpPrice(totalOrderPrice);
            createOrder.setTotalSellingPrice(totalOrderPrice);
            createOrder.setTotalItem(totalItem);
            createOrder.setShippingAddress(address);
            createOrder.setOrderStatus(OrderStatus.PENDING);
            createOrder.getPaymentDetails().setStatus(PaymentStatus.PENDING);

            OrderEntity savedOrder = orderRepository.save(createOrder);
            orders.add(savedOrder);

            List<OrderItemEntity>orderItems = new ArrayList<>();
            for(CartItemEntity item: items){
                OrderItemEntity orderItem = new OrderItemEntity();
                orderItem.setOrder(savedOrder);
                orderItem.setMrpPrice(item.getMrpPrice());
                orderItem.setProduct(item.getProduct());
                orderItem.setQuantity(item.getQuantity());
                orderItem.setSize(item.getSize());
                orderItem.setUserId(item.getUserId());
                orderItem.setSellingPrice(item.getSellingPrice());

                savedOrder.getOrderItems().add(orderItem);

                OrderItemEntity savedOrderItem=orderItemRepository.save(orderItem);
                orderItems.add(savedOrderItem);
            }

        }
        return orders;
    }

    @Override
    public OrderEntity findOrderById(Long id) throws Exception {
        return orderRepository.findById(id)
                .orElseThrow(()->new Exception("order not found..."));
    }

    @Override
    public List<OrderEntity> usersOrderHistory(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<OrderEntity> sellersOrder(Long sellerId) {
        return orderRepository.findBySellerId(sellerId);
    }

    @Override
    public OrderEntity updateOrderStatus(Long orderId, OrderStatus status) throws Exception {
        OrderEntity order = findOrderById(orderId);
        order.setOrderStatus(status);
        return orderRepository.save(order);
    }

    @Override
    public OrderEntity cancelOrder(Long orderId, User user) throws Exception {
        OrderEntity order = findOrderById(orderId);

        if(!user.getId().equals(order.getUser().getId())){
            throw new Exception("you don't have access to this order!");
        }
        order.setOrderStatus(OrderStatus.CANCELED);
        return orderRepository.save(order);
    }

    @Override
    public OrderItemEntity getOrderItemById(Long id) throws Exception {
        return orderItemRepository.findById(id)
                .orElseThrow(()->new Exception("order item not exist..."));
    }
}
