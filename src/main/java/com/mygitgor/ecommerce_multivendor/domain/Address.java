package com.mygitgor.ecommerce_multivendor.domain;

import com.mygitgor.ecommerce_multivendor.domain.abstraction.BaseModelId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address extends BaseModelId {
    private String name;
    private String locality;
    private String address;
    private String city;
    private String state;
    private String pinCode;
    private String mobile;
}
