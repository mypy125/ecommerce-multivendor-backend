package com.mygitgor.ecommerce_multivendor.domain.model;

import com.mygitgor.ecommerce_multivendor.domain.model.abstraction.BaseModelId;
import com.mygitgor.ecommerce_multivendor.domain.model.costant.HomeCategorySection;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomeCategory extends BaseModelId {
    private String name;
    private String image;
    private String categoryId;
    private HomeCategorySection section;
}
