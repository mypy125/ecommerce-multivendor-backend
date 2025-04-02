package com.mygitgor.ecommerce_multivendor.mapper;

import com.mygitgor.ecommerce_multivendor.domain.model.Product;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper extends ConfigMapper<Product, ProductEntity> {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
}
