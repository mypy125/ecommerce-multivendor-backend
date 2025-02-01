package com.mygitgor.ecommerce_multivendor.domain;

import com.mygitgor.ecommerce_multivendor.domain.abstraction.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Transaction extends BaseEntity {

    @ManyToOne
    private Users customer;

    @OneToOne
    private Order order;

    @ManyToOne
    private Seller seller;

    private LocalDateTime date = LocalDateTime.now();
}
