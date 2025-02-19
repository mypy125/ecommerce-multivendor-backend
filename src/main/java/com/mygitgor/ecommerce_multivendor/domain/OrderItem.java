package com.mygitgor.ecommerce_multivendor.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mygitgor.ecommerce_multivendor.domain.abstraction.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OrderItem extends BaseEntity {

    @JsonIgnore
    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    private String size;
    private int quantity;

    private Integer mrpPrice;
    private Integer sellingPrice;

    private Long userId;

}
