package com.mygitgor.ecommerce_multivendor.domain.repository;

import com.mygitgor.ecommerce_multivendor.domain.model.Address;

public interface AddressRepository {
    Address save(Address address);
}
