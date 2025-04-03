package com.mygitgor.ecommerce_multivendor.application.service.impl;

import com.mygitgor.ecommerce_multivendor.domain.model.Cart;
import com.mygitgor.ecommerce_multivendor.domain.model.Coupon;
import com.mygitgor.ecommerce_multivendor.domain.model.User;
import com.mygitgor.ecommerce_multivendor.domain.repository.CartRepository;
import com.mygitgor.ecommerce_multivendor.domain.repository.CouponRepository;
import com.mygitgor.ecommerce_multivendor.domain.repository.UserRepository;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    @Override
    public Cart applyCoupon(String code, double orderValue, User user) throws Exception {
        Coupon coupon = couponRepository.findByCode(code);
        Optional<Cart> cartOptional = cartRepository.findByUserId(user.getId());

        if(coupon == null){
            throw new Exception("Coupon not valid");
        }
        if(user.getUsedCoupons().contains(coupon)){
            throw new Exception("Coupon already used");
        }
        if(orderValue < coupon.getMinimumOrderValue()){
            throw new Exception("Valid for minimum order value " + coupon.getMinimumOrderValue());
        }
        if(coupon.isActive() &&
                LocalDate.now().isAfter(coupon.getValidityStartDate()) &&
                LocalDate.now().isBefore(coupon.getValidityEndDate()))
        {
            user.getUsedCoupons().add(coupon);
            userRepository.save(user);

            Cart cart = cartOptional.orElseThrow(() -> new Exception("Cart not found for user"));
            double discountPrice = (cart.getTotalSellingPrice() * coupon.getDiscountPercentage()) / 100;
            cart.setTotalSellingPrice(cart.getTotalSellingPrice() - discountPrice);
            cart.setCouponCode(code);
            cartRepository.save(cart);
            return cart;
        }
        throw new Exception("Coupon not valid");
    }

    @Override
    public Cart removeCoupon(String code, User user) throws Exception {
        Coupon coupon = couponRepository.findByCode(code);
        if(coupon == null){
            throw new Exception("Coupon not found");
        }
        Optional<Cart> cartOptional = cartRepository.findByUserId(user.getId());
        Cart cart = cartOptional.orElseThrow(() -> new Exception("Cart not found for user"));

        double discountPrice = (cart.getTotalSellingPrice() * coupon.getDiscountPercentage()) / 100;
        cart.setTotalSellingPrice(cart.getTotalSellingPrice() + discountPrice);
        cart.setCouponCode(null);

        return cartRepository.save(cart);
    }

    @Override
    public Coupon findCouponById(Long id) throws Exception {
        return couponRepository.findById(id).orElseThrow(() ->
                new Exception("Coupon not found"));
    }

    @Override
    @PreAuthorize("hasRole ('ADMIN')")
    public Coupon createCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    @Override
    public List<Coupon> findAllCoupon() {
        return couponRepository.findAll();
    }

    @Override
    @PreAuthorize("hasRole ('ADMIN')")
    public void deleteCoupon(Long id) throws Exception {
        findCouponById(id);
        couponRepository.deleteById(id);
    }
}
