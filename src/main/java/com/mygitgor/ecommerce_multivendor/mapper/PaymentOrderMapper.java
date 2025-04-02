package com.mygitgor.ecommerce_multivendor.mapper;

import com.mygitgor.ecommerce_multivendor.domain.model.PaymentOrder;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.PaymentOrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PaymentOrderMapper extends ConfigMapper<PaymentOrder, PaymentOrderEntity> {
    PaymentOrderMapper INSTANCE = Mappers.getMapper(PaymentOrderMapper.class);
}
