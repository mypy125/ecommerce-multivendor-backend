package com.mygitgor.ecommerce_multivendor.domain.abstraction;

import com.mygitgor.ecommerce_multivendor.domain.costant.USER_ROLE;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BasePerson extends BaseModelId {
    private String email;
    private String mobile;
    private String password;
    private USER_ROLE role;
}