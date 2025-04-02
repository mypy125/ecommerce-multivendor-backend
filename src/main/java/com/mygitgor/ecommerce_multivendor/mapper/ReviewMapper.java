package com.mygitgor.ecommerce_multivendor.mapper;

import com.mygitgor.ecommerce_multivendor.domain.model.Review;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.ReviewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReviewMapper extends ConfigMapper<Review, ReviewEntity> {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);
}
