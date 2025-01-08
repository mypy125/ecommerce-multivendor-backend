package com.mygitgor.ecommerce_multivendor.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CartItem extends BaseEntity<Long>{

    @ManyToOne
    @JsonIgnore
    private Cart cart;

    @OneToOne
    private Product product;

    private String size;
    private int quantity = 1;

    private Integer mrpPrice;
    private Integer sellingPrice;

    private Long userId;
}
