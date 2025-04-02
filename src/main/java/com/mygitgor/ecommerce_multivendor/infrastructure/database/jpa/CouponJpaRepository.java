package com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponJpaRepository extends JpaRepository<CouponEntity, Long> {
    CouponEntity findByCode(String code);
}
