package com.mygitgor.ecommerce_multivendor.application.service;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.Cart;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.CouponEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.UserEntity;

import java.util.List;

public interface CouponService {
    Cart applyCoupon(String code,double orderValue, UserEntity user) throws Exception;
    Cart removeCoupon(String code, UserEntity user) throws Exception;
    CouponEntity findCouponById(Long id) throws Exception;
    CouponEntity createCoupon(CouponEntity coupon);
    List<CouponEntity> findAllCoupon();
    void deleteCoupon(Long id) throws Exception;
}
