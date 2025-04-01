package com.mygitgor.ecommerce_multivendor.infrastructure.database;


import com.mygitgor.ecommerce_multivendor.infrastructure.database.abstraction.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class VerificationCode extends BaseEntity {
    private String otp;
    private String email;

    @OneToOne
    private UserEntity user;

    @OneToOne
    private Seller seller;
}
