package com.mygitgor.ecommerce_multivendor.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mygitgor.ecommerce_multivendor.domain.abstraction.BasePerson;
import com.mygitgor.ecommerce_multivendor.domain.costant.USER_ROLE;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "users")
public class User extends BasePerson {
    private String fullName;

    @OneToMany
    private Set<Address> addresses = new HashSet<>();

    @ManyToMany
    @JsonIgnore
    private Set<Coupon> usedCoupons = new HashSet<>();

    @PrePersist
    public void prePersist() {
        this.setRole(USER_ROLE.ROLE_CUSTOMER);
    }

}
