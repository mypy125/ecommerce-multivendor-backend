package com.mygitgor.ecommerce_multivendor.application.service;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.DealEntity;

import java.util.List;

public interface DealService {
    List<DealEntity> getDeals();
    DealEntity createDeal(DealEntity deal);
    DealEntity updateDeal(DealEntity deal, Long id) throws Exception;
    void deleteDeal(Long id) throws Exception;
}
