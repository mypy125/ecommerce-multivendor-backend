package com.mygitgor.ecommerce_multivendor.infrastructure.database;


import com.mygitgor.ecommerce_multivendor.infrastructure.database.abstraction.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "verification_code")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class VerificationCodeEntity extends BaseEntity {
    private String otp;
    private String email;

    @OneToOne
    private UserEntity user;

    @OneToOne
    private SellerEntity seller;
}
