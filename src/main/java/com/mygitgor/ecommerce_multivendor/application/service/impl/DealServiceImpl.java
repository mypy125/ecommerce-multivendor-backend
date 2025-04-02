package com.mygitgor.ecommerce_multivendor.application.service.impl;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.DealEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.HomeCategoryEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.DealJpaRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.HomeCategoryJpaRepository;
import com.mygitgor.ecommerce_multivendor.application.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DealServiceImpl implements DealService {
    private final HomeCategoryJpaRepository homeCategoryRepository;
    private final DealJpaRepository dealRepository;
    
    @Override
    public List<DealEntity> getDeals() {
        return dealRepository.findAll();
    }

    @Override
    public DealEntity createDeal(DealEntity deal) {
        HomeCategoryEntity category = homeCategoryRepository.findById(deal.getCategory().getId()).orElse(null);

        DealEntity newDeal = dealRepository.save(deal);
        newDeal.setCategory(category);
        newDeal.setDiscount(deal.getDiscount());
        return dealRepository.save(newDeal);
    }

    @Override
    public DealEntity updateDeal(DealEntity deal, Long id) throws Exception {
        DealEntity existDeal = dealRepository.findById(id).orElse(null);
        HomeCategoryEntity category = homeCategoryRepository.findById(deal.getCategory().getId()).orElse(null);

        if(existDeal!=null){
            if(existDeal.getDiscount()!=null){
                existDeal.setDiscount(deal.getDiscount());
            }
            if(category!=null){
                existDeal.setCategory(category);
            }
            return dealRepository.save(existDeal);
        }
        throw new Exception("deal not found");
    }

    @Override
    public void deleteDeal(Long id) throws Exception {
        DealEntity deal = dealRepository.findById(id)
                .orElseThrow(()-> new Exception("deal not found"));

        dealRepository.delete(deal);
    }
}
