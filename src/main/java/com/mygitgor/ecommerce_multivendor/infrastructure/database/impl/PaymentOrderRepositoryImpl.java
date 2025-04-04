package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.model.PaymentOrder;
import com.mygitgor.ecommerce_multivendor.domain.repository.PaymentOrderRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.PaymentOrderEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.PaymentOrderJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.PaymentOrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PaymentOrderRepositoryImpl implements PaymentOrderRepository {
    private final PaymentOrderJpaRepository jpaRepository;
    private final PaymentOrderMapper paymentOrderMapper;

    @Override
    public PaymentOrder save(PaymentOrder paymentOrder) {
        PaymentOrderEntity entity = paymentOrderMapper.toEntity(paymentOrder);
        PaymentOrderEntity savedEntity = jpaRepository.save(entity);
        return paymentOrderMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<PaymentOrder> findById(Long id) {
        return jpaRepository.findById(id).map(paymentOrderMapper::toDomain);
    }

    @Override
    public PaymentOrder findByPaymentLinkId(String paymentLinkId) {
        PaymentOrderEntity entity = jpaRepository.findByPaymentLinkId(paymentLinkId);
        return entity != null ? paymentOrderMapper.toDomain(entity) : null;
    }
}
