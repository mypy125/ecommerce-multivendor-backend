package com.mygitgor.ecommerce_multivendor.domain;


import com.mygitgor.ecommerce_multivendor.domain.abstraction.BaseEntity;
import com.mygitgor.ecommerce_multivendor.domain.costant.OrderStatus;
import com.mygitgor.ecommerce_multivendor.domain.costant.PaymentStatus;
import com.mygitgor.ecommerce_multivendor.domain.details.PaymentDetails;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "orders")
public class Order extends BaseEntity {

    private String orderId;

    @ManyToOne
    private User user;
    private Long sellerId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @ManyToOne
    private Address shippingAddress;

    @Embedded
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
