package com.mygitgor.ecommerce_multivendor.domain;

import com.mygitgor.ecommerce_multivendor.domain.abstraction.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Coupon extends BaseEntity {

    private String code;
    private double discountPercentage;
    private LocalDate validityStartDate;
    private LocalDate validityEndDate;
    private double minimumOrderValue;
    private boolean isActive = true;

    @ManyToMany(mappedBy = "usedCoupons")
    private Set<Users> usedByUsers = new HashSet<>();
}
