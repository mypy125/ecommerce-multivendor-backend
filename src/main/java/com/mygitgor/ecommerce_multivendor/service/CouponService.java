package com.mygitgor.ecommerce_multivendor.service;

import com.mygitgor.ecommerce_multivendor.domain.Cart;
import com.mygitgor.ecommerce_multivendor.domain.Coupon;
import com.mygitgor.ecommerce_multivendor.domain.Users;

import java.util.List;

public interface CouponService {
    Cart applyCoupon(String code,double orderValue, Users users);
    Cart removeCoupon(String code, Users users);
    Coupon findCouponById(Long id);
    Coupon createCoupon(Coupon coupon);
    List<Coupon> findAllCoupon();
    void deleteCoupon(Long id);
}
