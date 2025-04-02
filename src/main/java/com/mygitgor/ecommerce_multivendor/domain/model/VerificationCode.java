package com.mygitgor.ecommerce_multivendor.domain.model;

import com.mygitgor.ecommerce_multivendor.domain.model.abstraction.BaseModelId;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerificationCode extends BaseModelId {
    private String otp;
    private String email;
    private User user;
    private Seller seller;
}
