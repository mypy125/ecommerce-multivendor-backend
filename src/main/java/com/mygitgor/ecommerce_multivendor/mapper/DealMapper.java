package com.mygitgor.ecommerce_multivendor.mapper;

import com.mygitgor.ecommerce_multivendor.domain.model.Deal;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.DealEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DealMapper extends ConfigMapper<Deal, DealEntity> {
    DealMapper INSTANCE = Mappers.getMapper(DealMapper.class);
}
