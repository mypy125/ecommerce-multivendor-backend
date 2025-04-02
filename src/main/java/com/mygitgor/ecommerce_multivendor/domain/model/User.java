package com.mygitgor.ecommerce_multivendor.domain.model;

import com.mygitgor.ecommerce_multivendor.domain.model.abstraction.BasePerson;
import com.mygitgor.ecommerce_multivendor.domain.model.costant.USER_ROLE;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class User extends BasePerson {
    private String fullName;
    private Set<Address> addresses = new HashSet<>();
    private Set<Coupon> usedCoupons = new HashSet<>();

    public User() {
        this.setRole(USER_ROLE.ROLE_CUSTOMER);
    }
}

