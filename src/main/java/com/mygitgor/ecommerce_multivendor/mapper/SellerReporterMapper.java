package com.mygitgor.ecommerce_multivendor.mapper;

import com.mygitgor.ecommerce_multivendor.domain.model.SellerReport;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.SellerReportEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SellerReporterMapper extends ConfigMapper<SellerReport, SellerReportEntity> {
    SellerReporterMapper INSTANCE = Mappers.getMapper(SellerReporterMapper.class);
}
