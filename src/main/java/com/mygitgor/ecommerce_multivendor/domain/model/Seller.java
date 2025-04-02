package com.mygitgor.ecommerce_multivendor.domain.model;

import com.mygitgor.ecommerce_multivendor.domain.model.abstraction.BasePerson;
import com.mygitgor.ecommerce_multivendor.domain.model.costant.AccountStatus;
import com.mygitgor.ecommerce_multivendor.domain.model.details.BankDetails;
import com.mygitgor.ecommerce_multivendor.domain.model.details.BusinessDetails;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Seller extends BasePerson {
    private String sellerName;
    private BusinessDetails businessDetails = new BusinessDetails();
    private BankDetails bankDetails = new BankDetails();
    private Address pickupAddress = new Address();
    private String GSTIN;
    private boolean isEmailVerified = false;
    private AccountStatus accountStatus = AccountStatus.PENDING_VERIFICATION;
}
