package com.mygitgor.ecommerce_multivendor.application.service.impl;

import com.mygitgor.ecommerce_multivendor.domain.model.User;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.CartEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.CouponEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.CartJpaRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.CouponJpaRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.UserJpaRepository;
import com.mygitgor.ecommerce_multivendor.application.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
    private final CouponJpaRepository couponRepository;
    private final CartJpaRepository cartRepository;
    private final UserJpaRepository userRepository;
    
    @Override
    public CartEntity applyCoupon(String code, double orderValue, User user) throws Exception {
        CouponEntity coupon = couponRepository.findByCode(code);
        CartEntity cart = cartRepository.findByUserId(user.getId());

        if(coupon==null){
            throw new Exception("coupon not valid");
        }
        if(user.getUsedCoupons().contains(coupon)){
            throw new Exception("coupon already used");
        }
        if(orderValue<coupon.getMinimumOrderValue()){
            throw new Exception("valid for minimum order value "+coupon.getMinimumOrderValue());
        }
        if(coupon.isActive() &&
                LocalDate.now().isAfter(coupon.getValidityStartDate())
                && LocalDate.now().isBefore(coupon.getValidityEndDate()))
        {
            user.getUsedCoupons().add(coupon);
            userRepository.save(user);
            double discountPrice = (cart.getTotalSellingPrice()*coupon.getDiscountPercentage())/100;
            cart.setTotalSellingPrice(cart.getTotalSellingPrice()-discountPrice);
            cart.setCouponCode(code);
            cartRepository.save(cart);
            return cart;
        }
        throw new Exception("coupon not valid");
    }

    @Override
    public CartEntity removeCoupon(String code, User user) throws Exception {
        CouponEntity coupon = couponRepository.findByCode(code);
        if(coupon==null){
            throw new Exception("coupon not found...");
        }
        CartEntity cart = cartRepository.findByUserId(user.getId());
        double discountPrice = (cart.getTotalSellingPrice()*coupon.getDiscountPercentage())/100;
        cart.setTotalSellingPrice(cart.getTotalSellingPrice()+discountPrice);
        cart.setCouponCode(null);

        return cartRepository.save(cart);
    }

    @Override
    public CouponEntity findCouponById(Long id) throws Exception {
        return couponRepository.findById(id).orElseThrow(()->
                 new Exception("coupon not found"));
    }

    @Override
    @PreAuthorize("hasRole ('ADMIN')")
    public CouponEntity createCoupon(CouponEntity coupon) {
        return couponRepository.save(coupon);
    }

    @Override
    public List<CouponEntity> findAllCoupon() {
        return couponRepository.findAll();
    }

    @Override
    @PreAuthorize("hasRole ('ADMIN')")
    public void deleteCoupon(Long id) throws Exception {
        findCouponById(id);
        couponRepository.deleteById(id);
    }
}
