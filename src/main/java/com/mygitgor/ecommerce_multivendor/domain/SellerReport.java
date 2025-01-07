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
public class SellerReport extends BaseEntity<Long>{

    @OneToOne
    private Seller seller;

    private Long totalEarnings=0L;
    private Long totalSales=0L;
    private Long totalRefunds=0L;
    private Long totalTax=0L;
    private Long netEarnings=0L;

    private Integer totalOrders=0;
    private Integer canceledOrders=0;
    private Integer totalTransactions=0;
}
