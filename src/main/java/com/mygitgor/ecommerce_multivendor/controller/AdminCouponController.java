package com.mygitgor.ecommerce_multivendor.controller;

import com.mygitgor.ecommerce_multivendor.domain.Cart;
import com.mygitgor.ecommerce_multivendor.domain.Coupon;
import com.mygitgor.ecommerce_multivendor.domain.User;
import com.mygitgor.ecommerce_multivendor.service.CartService;
import com.mygitgor.ecommerce_multivendor.service.CouponService;
import com.mygitgor.ecommerce_multivendor.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/coupons")
public class AdminCouponController {
    private final CouponService couponService;
    private final UserService userService;
    private final CartService cartService;

    @PostMapping("/apply")
    public ResponseEntity<Cart>applyCoupon(@RequestParam String apply,
                                           @RequestParam String code,
                                           @RequestParam double orderValue,
                                           @RequestHeader("Authorization")
                                               String jwt) throws Exception
    {
        User user = userService.findByJwtToken(jwt);
        boolean isApply = Boolean.parseBoolean(apply);
        Cart cart = isApply ? couponService.applyCoupon(code, orderValue, user)
                : couponService.removeCoupon(code, user);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/admin/create")
    public ResponseEntity<Coupon>createCoupon(@RequestBody Coupon coupon){
        Coupon createCoupon = couponService.createCoupon(coupon);
        return ResponseEntity.ok(createCoupon);
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<?>deleteCoupon(@PathVariable Long id) throws Exception {
        couponService.deleteCoupon(id);
        return ResponseEntity.ok("deleted coupon successfully!");
    }

    @GetMapping("/admin/all")
    public ResponseEntity<List<Coupon>>getAllCoupons() {
        List<Coupon>coupons = couponService.findAllCoupon();
        return ResponseEntity.ok(coupons);
    }
}
