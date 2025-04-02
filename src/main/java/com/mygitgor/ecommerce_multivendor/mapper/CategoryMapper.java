package com.mygitgor.ecommerce_multivendor.mapper;

import com.mygitgor.ecommerce_multivendor.domain.model.Category;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends ConfigMapper<Category, CategoryEntity> {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
}
