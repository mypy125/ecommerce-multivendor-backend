package com.mygitgor.ecommerce_multivendor.infrastructure.database;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.abstraction.BaseEntity;
import com.mygitgor.ecommerce_multivendor.domain.costant.HomeCategorySection;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class HomeCategory extends BaseEntity {
    private String name;
    private String image;
    private String categoryId;
    private HomeCategorySection section;
}
