package com.mygitgor.ecommerce_multivendor.mapper;

import com.mygitgor.ecommerce_multivendor.domain.model.Seller;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.SellerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SellerMapper extends ConfigMapper<Seller, SellerEntity> {
    SellerMapper INSTANCE = Mappers.getMapper(SellerMapper.class);
}
