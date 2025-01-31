package com.mygitgor.ecommerce_multivendor.service.impl;

import com.mygitgor.ecommerce_multivendor.domain.Cart;
import com.mygitgor.ecommerce_multivendor.domain.Coupon;
import com.mygitgor.ecommerce_multivendor.domain.User;
import com.mygitgor.ecommerce_multivendor.repository.CouponRepository;
import com.mygitgor.ecommerce_multivendor.service.CouponService;
import com.mygitgor.ecommerce_multivendor.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    private final UserService userService;
    
    @Override
    public Cart applyCoupon(String code, double orderValue, User user) {
        return null;
    }

    @Override
    public Cart removeCoupon(String code, User user) {
        return null;
    }

    @Override
    public Coupon findCouponById(Long id) {
        return null;
    }

    @Override
    public Coupon createCoupon(Coupon coupon) {
        return null;
    }

    @Override
    public List<Coupon> findAllCoupon() {
        return List.of();
    }

    @Override
    public void deleteCoupon(Long id) {

    }
}
