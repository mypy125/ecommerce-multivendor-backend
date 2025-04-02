package com.mygitgor.ecommerce_multivendor.infrastructure.database.impl;

import com.mygitgor.ecommerce_multivendor.domain.model.Address;
import com.mygitgor.ecommerce_multivendor.domain.repository.AddressRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.AddressEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.AddressJpaRepository;
import com.mygitgor.ecommerce_multivendor.mapper.AddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AddressRepositoryImpl implements AddressRepository {
    private final AddressJpaRepository jpaRepository;
    private final AddressMapper addressMapper;

    @Override
    public Address save(Address address) {
        AddressEntity entity = addressMapper.toEntity(address);
        AddressEntity savedEntity = jpaRepository.save(entity);
        return addressMapper.toDomain(savedEntity);
    }
}
