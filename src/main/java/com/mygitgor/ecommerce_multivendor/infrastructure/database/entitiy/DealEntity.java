package com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.abstraction.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "deal")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DealEntity extends BaseEntity{
    private Integer discount;

    @OneToOne
    private HomeCategoryEntity category;
}
