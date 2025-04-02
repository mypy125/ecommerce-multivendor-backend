package com.mygitgor.ecommerce_multivendor.mapper;

import com.mygitgor.ecommerce_multivendor.domain.model.Cart;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.CartEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CartMapper extends ConfigMapper<Cart, CartEntity> {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);
}
