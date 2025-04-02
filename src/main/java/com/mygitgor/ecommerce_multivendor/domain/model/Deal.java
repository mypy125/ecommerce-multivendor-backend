package com.mygitgor.ecommerce_multivendor.domain.model;

import com.mygitgor.ecommerce_multivendor.domain.model.abstraction.BaseModelId;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Deal extends BaseModelId {
    private Integer discount;
    private HomeCategory category;
}
