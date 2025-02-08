package com.mygitgor.ecommerce_multivendor.repository;

import com.mygitgor.ecommerce_multivendor.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Coupon findByCode(String code);
}
