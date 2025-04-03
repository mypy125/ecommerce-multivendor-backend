package com.mygitgor.ecommerce_multivendor.domain.repository;

import com.mygitgor.ecommerce_multivendor.domain.model.Coupon;

import java.util.List;
import java.util.Optional;

public interface CouponRepository {
    Coupon findByCode(String code);
    Optional<Coupon> findById(Long id);
    List<Coupon> findAll();
    Coupon save(Coupon coupon);
    void deleteById(Long id);
}
