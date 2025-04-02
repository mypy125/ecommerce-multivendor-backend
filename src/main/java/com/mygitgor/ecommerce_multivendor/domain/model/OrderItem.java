package com.mygitgor.ecommerce_multivendor.domain.model;

import com.mygitgor.ecommerce_multivendor.domain.model.abstraction.BaseModelId;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem extends BaseModelId {private Order order;
    private Product product;
    private String size;
    private int quantity;
    private Integer mrpPrice;
    private Integer sellingPrice;
    private Long userId;

}
