package com.mygitgor.ecommerce_multivendor.domain;


import com.mygitgor.ecommerce_multivendor.domain.abstraction.BasePerson;
import com.mygitgor.ecommerce_multivendor.domain.costant.AccountStatus;
import com.mygitgor.ecommerce_multivendor.domain.costant.USER_ROLE;
import com.mygitgor.ecommerce_multivendor.domain.details.BankDetails;
import com.mygitgor.ecommerce_multivendor.domain.details.BusinessDetails;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Seller extends BasePerson {
    private String sellerName;

    @Embedded
    private BusinessDetails businessDetails = new BusinessDetails();

    @Embedded
    private BankDetails bankDetails = new BankDetails();

    @OneToOne(cascade = CascadeType.ALL)
    private Address pickupAddress = new Address();

    private String NDS;

    private boolean isEmailVerified = false;

    private AccountStatus accountStatus = AccountStatus.PENDING_VERIFICATION;

    @PrePersist
    public void prePersist() {
        this.setRole(USER_ROLE.ROLE_SELLER);
    }

}
