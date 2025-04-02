package com.mygitgor.ecommerce_multivendor.domain.model;

import com.mygitgor.ecommerce_multivendor.domain.model.abstraction.BaseModelId;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem extends BaseModelId {
    private Cart cart;
    private Product product;
    private String size;
    private int quantity = 1;
    private Integer mrpPrice;
    private Integer sellingPrice;
    private Long userId;
}
