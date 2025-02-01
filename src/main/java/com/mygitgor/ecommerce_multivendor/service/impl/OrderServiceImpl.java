package com.mygitgor.ecommerce_multivendor.service.impl;

import com.mygitgor.ecommerce_multivendor.domain.*;
import com.mygitgor.ecommerce_multivendor.domain.costant.OrderStatus;
import com.mygitgor.ecommerce_multivendor.domain.costant.PaymentStatus;
import com.mygitgor.ecommerce_multivendor.repository.AddressRepository;
import com.mygitgor.ecommerce_multivendor.repository.OrderItemRepository;
import com.mygitgor.ecommerce_multivendor.repository.OrderRepository;
import com.mygitgor.ecommerce_multivendor.service.OrderService;
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
    public Set<Order> createOrder(Users users,
                                  Address shippingAddress,
                                  Cart cart
    ) {
        if(!users.getAddresses().contains(shippingAddress)){
            users.getAddresses().add(shippingAddress);
        }
        Address address = addressRepository.save(shippingAddress);

        Map<Long, List<CartItem>>itemsBySeller=cart.getCartItems().stream()
                .collect(Collectors.groupingBy(item ->
                        item.getProduct().getSeller().getId()));

        Set<Order>orders = new HashSet<>();
        for(Map.Entry<Long, List<CartItem>> entry : itemsBySeller.entrySet()){
            Long sellerId= entry.getKey();
            List<CartItem>items = entry.getValue();

            int totalOrderPrice = items.stream().mapToInt(
                    CartItem::getSellingPrice
            ).sum();

            int totalItem = items.stream().mapToInt(CartItem::getQuantity).sum();

            Order createOrder = new Order();
            createOrder.setUsers(users);
            createOrder.setSellerId(sellerId);
            createOrder.setTotalMrpPrice(totalOrderPrice);
            createOrder.setTotalSellingPrice(totalOrderPrice);
            createOrder.setTotalItem(totalItem);
            createOrder.setShippingAddress(address);
            createOrder.setOrderStatus(OrderStatus.PENDING);
            createOrder.getPaymentDetails().setStatus(PaymentStatus.PENDING);

            Order savedOrder = orderRepository.save(createOrder);
            orders.add(savedOrder);

            List<OrderItem>orderItems = new ArrayList<>();
            for(CartItem item: items){
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(savedOrder);
                orderItem.setMrpPrice(item.getMrpPrice());
                orderItem.setProduct(item.getProduct());
                orderItem.setQuantity(item.getQuantity());
                orderItem.setSize(item.getSize());
                orderItem.setUserId(item.getUserId());
                orderItem.setSellingPrice(item.getSellingPrice());

                savedOrder.getOrderItems().add(orderItem);

                OrderItem savedOrderItem=orderItemRepository.save(orderItem);
                orderItems.add(savedOrderItem);
            }

        }
        return orders;
    }

    @Override
    public Order findOrderById(Long id) throws Exception {
        return orderRepository.findById(id)
                .orElseThrow(()->new Exception("order not found..."));
    }

    @Override
    public List<Order> usersOrderHistory(Long userId) {
        return orderRepository.findByUsersId(userId);
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
    public Order cancelOrder(Long orderId, Users users) throws Exception {
        Order order = findOrderById(orderId);

        if(!users.getId().equals(order.getUsers().getId())){
            throw new Exception("you don't have access to this order!");
        }
        order.setOrderStatus(OrderStatus.CANCELED);
        return orderRepository.save(order);
    }

    @Override
    public OrderItem getOrderItemById(Long id) throws Exception {
        return orderItemRepository.findById(id)
                .orElseThrow(()->new Exception("order item not exist..."));
    }
}
