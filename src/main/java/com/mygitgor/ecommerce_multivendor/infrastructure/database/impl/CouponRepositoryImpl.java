package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.model.Coupon;
import com.mygitgor.ecommerce_multivendor.domain.repository.CouponRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.CouponEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.CouponJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.CouponMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CouponRepositoryImpl implements CouponRepository {
    private final CouponJpaRepository jpaRepository;
    private final CouponMapper couponMapper;

    @Override
    public Coupon findByCode(String code) {
        CouponEntity entity = jpaRepository.findByCode(code);
        return couponMapper.toDomain(entity);
    }

    @Override
    public Optional<Coupon> findById(Long id) {
        return jpaRepository.findById(id).map(couponMapper::toDomain);
    }

    @Override
    public List<Coupon> findAll() {
        List<CouponEntity> entities = jpaRepository.findAll();
        return entities.stream()
                .map(couponMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Coupon save(Coupon coupon) {
        CouponEntity entity = couponMapper.toEntity(coupon);
        return couponMapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }


}
