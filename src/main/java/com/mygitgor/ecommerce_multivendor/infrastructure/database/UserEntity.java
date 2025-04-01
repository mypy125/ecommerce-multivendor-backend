package com.mygitgor.ecommerce_multivendor.infrastructure.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.abstraction.BasePerson;
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
public class UserEntity extends BasePerson {
    private String fullName;

    @OneToMany
    private Set<AddressEntity> addresses = new HashSet<>();

    @ManyToMany
    @JsonIgnore
    private Set<CouponEntity> usedCoupons = new HashSet<>();

    @PrePersist
    public void prePersist() {
        this.setRole(USER_ROLE.ROLE_CUSTOMER);
    }

}
