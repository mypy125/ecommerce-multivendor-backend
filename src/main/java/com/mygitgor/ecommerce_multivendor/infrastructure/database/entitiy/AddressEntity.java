package com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.abstraction.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AddressEntity extends BaseEntity {
    private String name;
    private String locality;
    private String address;
    private String city;
    private String state;
    private String pinCode;
    private String mobile;
}
