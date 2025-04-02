package com.mygitgor.ecommerce_multivendor.domain.model;

import com.mygitgor.ecommerce_multivendor.domain.model.abstraction.BaseModelId;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction extends BaseModelId {
    private User customer;
    private Order order;
    private Seller seller;
    private LocalDateTime date = LocalDateTime.now();
}
