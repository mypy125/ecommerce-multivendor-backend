package com.mygitgor.ecommerce_multivendor.mapper;

import com.mygitgor.ecommerce_multivendor.domain.model.HomeCategory;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.HomeCategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface HomeCategoryMapper extends ConfigMapper<HomeCategory, HomeCategoryEntity> {
    HomeCategoryMapper INSTANCE = Mappers.getMapper(HomeCategoryMapper.class);
}
