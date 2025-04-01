package com.mygitgor.ecommerce_multivendor.infrastructure.database;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.abstraction.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "transaction")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TransactionEntity extends BaseEntity {

    @ManyToOne
    private UserEntity customer;

    @OneToOne
    private OrderEntity order;

    @ManyToOne
    private SellerEntity seller;

    private LocalDateTime date = LocalDateTime.now();
}
