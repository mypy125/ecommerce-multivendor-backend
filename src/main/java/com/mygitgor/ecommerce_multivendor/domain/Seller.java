package com.mygitgor.ecommerce_multivendor.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Seller extends BaseEntity<Long>{
    private String sellerName;
    private String mobile;

    @Column(unique = true, nullable = false)
    private String email;
    private String password;

}
