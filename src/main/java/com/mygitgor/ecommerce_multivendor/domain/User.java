package com.mygitgor.ecommerce_multivendor.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity<Long>{
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String email;
    private String fullName;
    private String mobile;

    private USER_ROLE role = USER_ROLE.ROLE_CUSTOMER;

    private Set<Address> addresses = new HashSet<>();

    private Set<Coupon> usedCoupons = new HashSet<>();


}
