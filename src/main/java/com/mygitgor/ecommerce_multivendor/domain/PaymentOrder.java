package com.mygitgor.ecommerce_multivendor.domain;

import com.mygitgor.ecommerce_multivendor.domain.abstraction.BaseEntity;
import com.mygitgor.ecommerce_multivendor.domain.costant.PaymentMethod;
import com.mygitgor.ecommerce_multivendor.domain.costant.PaymentOrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PaymentOrder extends BaseEntity {
    private Long amount;

    private PaymentOrderStatus status = PaymentOrderStatus.PENDING;
    private PaymentMethod paymentMethod;

    private String paymentLinkId;

    @ManyToOne
    private Users users;

    @OneToMany
    private Set<Order> orders = new HashSet<>();

}
