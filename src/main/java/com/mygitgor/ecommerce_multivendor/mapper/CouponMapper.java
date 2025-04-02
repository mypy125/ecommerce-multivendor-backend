package com.mygitgor.ecommerce_multivendor.mapper;

import com.mygitgor.ecommerce_multivendor.domain.model.Coupon;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.CouponEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CouponMapper extends ConfigMapper<Coupon, CouponEntity> {
    CouponMapper INSTANCE = Mappers.getMapper(CouponMapper.class);
}
