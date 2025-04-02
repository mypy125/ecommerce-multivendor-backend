package com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.abstraction.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CategoryEntity extends BaseEntity {
    private String name;

    @Column(unique = true)
    @NotNull
    private String categoryId;

    @ManyToOne
    private CategoryEntity parentCategory;

    @NotNull
    private Integer level;


}
