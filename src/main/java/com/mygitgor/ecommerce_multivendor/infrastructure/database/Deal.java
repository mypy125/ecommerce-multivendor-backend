package com.mygitgor.ecommerce_multivendor.infrastructure.database;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.abstraction.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Deal extends BaseEntity{
    private Integer discount;

    @OneToOne
    private HomeCategory category;
}
