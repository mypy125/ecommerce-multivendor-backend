package com.mygitgor.ecommerce_multivendor.service;

import com.mygitgor.ecommerce_multivendor.domain.Cart;
import com.mygitgor.ecommerce_multivendor.domain.Coupon;
import com.mygitgor.ecommerce_multivendor.domain.User;

import java.util.List;

public interface CouponService {
    Cart applyCoupon(String code,double orderValue, User user);
    Cart removeCoupon(String code, User user);
    Coupon findCouponById(Long id);
    Coupon createCoupon(Coupon coupon);
    List<Coupon> findAllCoupon();
    void deleteCoupon(Long id);
}
