package com.mygitgor.ecommerce_multivendor.domain.model;

import com.mygitgor.ecommerce_multivendor.domain.model.abstraction.BaseModelId;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart extends BaseModelId {
    private User user;
    private Set<CartItem> cartItems = new HashSet<>();
    private double totalSellingPrice;
    private int totalItem;
    private int totalMrpPrice;
    private int discount;
    private String couponCode;
}
