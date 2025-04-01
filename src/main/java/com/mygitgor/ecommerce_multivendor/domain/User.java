package com.mygitgor.ecommerce_multivendor.domain;

import com.mygitgor.ecommerce_multivendor.domain.abstraction.BasePerson;
import com.mygitgor.ecommerce_multivendor.domain.costant.USER_ROLE;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class User extends BasePerson {
    private String fullName;
    private Set<Address> addresses = new HashSet<>();
    private Set<Coupon> usedCoupons = new HashSet<>();

    public User() {
        this.setRole(USER_ROLE.ROLE_CUSTOMER);
    }
}

