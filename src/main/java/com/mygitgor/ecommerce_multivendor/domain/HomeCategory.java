package com.mygitgor.ecommerce_multivendor.domain;

import com.mygitgor.ecommerce_multivendor.domain.costant.HomeCategorySection;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class HomeCategory extends BaseEntity<Long> {
    private String name;
    private String image;
    private String categoryId;
    private HomeCategorySection section;
}
