package com.mygitgor.ecommerce_multivendor.domain.model.abstraction;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseModelId {
    private Long id;
}
