package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.repository.CouponRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.CouponJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.CouponMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CouponRepositoryImpl implements CouponRepository {
    private final CouponJpaRepository jpaRepository;
    private final CouponMapper couponMapper;

}
