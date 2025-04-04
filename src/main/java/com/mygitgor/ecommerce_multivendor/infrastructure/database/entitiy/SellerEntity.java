package com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy;


import com.mygitgor.ecommerce_multivendor.infrastructure.database.abstraction.BasePerson;
import com.mygitgor.ecommerce_multivendor.domain.model.costant.AccountStatus;
import com.mygitgor.ecommerce_multivendor.domain.model.costant.USER_ROLE;
import com.mygitgor.ecommerce_multivendor.domain.model.details.BankDetails;
import com.mygitgor.ecommerce_multivendor.domain.model.details.BusinessDetails;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "seller")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SellerEntity extends BasePerson {
    private String sellerName;

    @Embedded
    private BusinessDetails businessDetails = new BusinessDetails();

    @Embedded
    private BankDetails bankDetails = new BankDetails();

    @OneToOne(cascade = CascadeType.ALL)
    private AddressEntity pickupAddress = new AddressEntity();

    private String GSTIN;

    private boolean isEmailVerified = false;

    private AccountStatus accountStatus = AccountStatus.PENDING_VERIFICATION;

    @PrePersist
    public void prePersist() {
        this.setRole(USER_ROLE.ROLE_SELLER);
    }

}
