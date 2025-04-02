package com.mygitgor.ecommerce_multivendor.mapper;

import com.mygitgor.ecommerce_multivendor.domain.model.OrderItem;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.OrderItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderItemMapper extends ConfigMapper<OrderItem, OrderItemEntity> {
    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);
}
