package com.mygitgor.ecommerce_multivendor.mapper;

import com.mygitgor.ecommerce_multivendor.domain.Coupon;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.CouponEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CouponMapper extends ConfigMapper<Coupon, CouponEntity> {
    CouponMapper INSTANCE = Mappers.getMapper(CouponMapper.class);
}
