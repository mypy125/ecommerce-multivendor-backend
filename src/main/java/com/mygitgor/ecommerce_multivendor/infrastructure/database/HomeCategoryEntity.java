package com.mygitgor.ecommerce_multivendor.infrastructure.database;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.abstraction.BaseEntity;
import com.mygitgor.ecommerce_multivendor.domain.costant.HomeCategorySection;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "home_category")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class HomeCategoryEntity extends BaseEntity {
    private String name;
    private String image;
    private String categoryId;
    private HomeCategorySection section;
}
