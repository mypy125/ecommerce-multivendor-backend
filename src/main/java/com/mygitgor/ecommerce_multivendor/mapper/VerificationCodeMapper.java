package com.mygitgor.ecommerce_multivendor.mapper;

import com.mygitgor.ecommerce_multivendor.domain.model.VerificationCode;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.VerificationCodeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VerificationCodeMapper extends ConfigMapper<VerificationCode, VerificationCodeEntity> {
    VerificationCodeMapper INSTANCE = Mappers.getMapper(VerificationCodeMapper.class);
}
