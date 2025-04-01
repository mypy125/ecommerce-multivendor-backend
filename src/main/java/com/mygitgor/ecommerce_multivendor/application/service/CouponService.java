package com.mygitgor.ecommerce_multivendor.application.service;

import com.mygitgor.ecommerce_multivendor.domain.User;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.CartEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.CouponEntity;

import java.util.List;

public interface CouponService {
    CartEntity applyCoupon(String code, double orderValue, User user) throws Exception;
    CartEntity removeCoupon(String code, User user) throws Exception;
    CouponEntity findCouponById(Long id) throws Exception;
    CouponEntity createCoupon(CouponEntity coupon);
    List<CouponEntity> findAllCoupon();
    void deleteCoupon(Long id) throws Exception;
}
