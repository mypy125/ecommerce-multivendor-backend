package com.mygitgor.ecommerce_multivendor.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Deal extends BaseEntity<Long> {

    private Integer discount;

    @OneToOne
    private HomeCategory category;
}
