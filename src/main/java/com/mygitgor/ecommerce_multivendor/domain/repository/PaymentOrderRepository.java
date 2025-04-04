package com.mygitgor.ecommerce_multivendor.domain.repository;

import com.mygitgor.ecommerce_multivendor.domain.model.PaymentOrder;

import java.util.Optional;

public interface PaymentOrderRepository {
    PaymentOrder save(PaymentOrder paymentOrder);
    Optional<PaymentOrder> findById(Long id);
    PaymentOrder findByPaymentLinkId(String paymentLinkId);
}
