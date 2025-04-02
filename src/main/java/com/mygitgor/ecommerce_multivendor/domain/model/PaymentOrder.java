package com.mygitgor.ecommerce_multivendor.domain.model;

import com.mygitgor.ecommerce_multivendor.domain.model.abstraction.BaseModelId;
import com.mygitgor.ecommerce_multivendor.domain.model.costant.PaymentMethod;
import com.mygitgor.ecommerce_multivendor.domain.model.costant.PaymentOrderStatus;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentOrder extends BaseModelId {
    private Long amount;
    private PaymentOrderStatus status = PaymentOrderStatus.PENDING;
    private PaymentMethod paymentMethod;
    private String paymentLinkId;
    private User user;
    private Set<Order> orders = new HashSet<>();
}
