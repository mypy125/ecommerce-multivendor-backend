package com.mygitgor.ecommerce_multivendor.infrastructure.database;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.abstraction.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Category extends BaseEntity {
    private String name;

    @Column(unique = true)
    @NotNull
    private String categoryId;

    @ManyToOne
    private Category parentCategory;

    @NotNull
    private Integer level;


}
