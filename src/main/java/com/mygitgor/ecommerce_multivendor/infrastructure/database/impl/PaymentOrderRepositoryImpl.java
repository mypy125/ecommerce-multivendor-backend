package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.repository.PaymentOrderRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.PaymentOrderJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.PaymentOrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentOrderRepositoryImpl implements PaymentOrderRepository {
    private final PaymentOrderJpaRepository jpaRepository;
    private final PaymentOrderMapper paymentOrderMapper;
}
