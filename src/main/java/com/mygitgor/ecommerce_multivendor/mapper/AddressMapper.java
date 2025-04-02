package com.mygitgor.ecommerce_multivendor.mapper;

import com.mygitgor.ecommerce_multivendor.domain.model.Address;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper extends ConfigMapper<Address, AddressEntity>{
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);
}
