package com.mygitgor.ecommerce_multivendor.domain.model.abstraction;

import com.mygitgor.ecommerce_multivendor.domain.model.costant.USER_ROLE;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BasePerson extends BaseModelId {
    private String email;
    private String mobile;
    private String password;
    private USER_ROLE role;
}