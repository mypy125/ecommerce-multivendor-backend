package com.mygitgor.ecommerce_multivendor.mapper;

import com.mygitgor.ecommerce_multivendor.domain.model.User;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper extends ConfigMapper<User, UserEntity> {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
}
