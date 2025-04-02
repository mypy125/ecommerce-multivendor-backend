package com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.abstraction.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "cart_item")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CartItemEntity extends BaseEntity {

    @ManyToOne
    @JsonIgnore
    private CartEntity cart;

    @OneToOne
    private ProductEntity product;

    private String size;
    private int quantity = 1;

    private Integer mrpPrice;
    private Integer sellingPrice;

    private Long userId;
}
