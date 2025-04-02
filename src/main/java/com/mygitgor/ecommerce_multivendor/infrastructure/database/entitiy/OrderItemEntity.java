package com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.abstraction.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "order_item")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OrderItemEntity extends BaseEntity {

    @JsonIgnore
    @ManyToOne
    private OrderEntity order;

    @ManyToOne
    private ProductEntity product;

    private String size;
    private int quantity;

    private Integer mrpPrice;
    private Integer sellingPrice;

    private Long userId;

}
