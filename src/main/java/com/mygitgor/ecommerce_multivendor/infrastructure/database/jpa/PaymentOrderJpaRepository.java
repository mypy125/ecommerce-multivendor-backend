package com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.PaymentOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentOrderJpaRepository extends JpaRepository<PaymentOrderEntity, Long> {
    PaymentOrderEntity findByPaymentLinkId(String paymentId);
}
