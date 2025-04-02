package com.mygitgor.ecommerce_multivendor.mapper;

import com.mygitgor.ecommerce_multivendor.domain.model.Wishlist;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.WishlistEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WishlistMapper extends ConfigMapper<Wishlist, WishlistEntity> {
    WishlistMapper INSTANCE = Mappers.getMapper(WishlistMapper.class);
}
