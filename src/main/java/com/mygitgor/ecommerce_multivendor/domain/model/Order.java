package com.mygitgor.ecommerce_multivendor.domain.model;

import com.mygitgor.ecommerce_multivendor.domain.model.abstraction.BaseModelId;
import com.mygitgor.ecommerce_multivendor.domain.model.costant.OrderStatus;
import com.mygitgor.ecommerce_multivendor.domain.model.costant.PaymentStatus;
import com.mygitgor.ecommerce_multivendor.domain.model.details.PaymentDetails;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseModelId {
    private String orderId;
    private User user;
    private Long sellerId;
    private List<OrderItem> orderItems = new ArrayList<>();
    private Address shippingAddress;
    private PaymentDetails paymentDetails = new PaymentDetails();
    private double totalMrpPrice;
    private Integer totalSellingPrice;
    private Integer discount;
    private OrderStatus orderStatus;
    private int totalItem;
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;
    private LocalDateTime orderDate = LocalDateTime.now();
    private LocalDateTime deliverDate = orderDate.plusDays(7);
}
