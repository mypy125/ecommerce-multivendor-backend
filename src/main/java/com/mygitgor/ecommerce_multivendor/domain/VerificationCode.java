package com.mygitgor.ecommerce_multivendor.domain;


import com.mygitgor.ecommerce_multivendor.domain.abstraction.BaseEntity;
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
    private Users users;

    @OneToOne
    private Seller seller;
}
