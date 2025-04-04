package com.mygitgor.ecommerce_multivendor.application.impl;

import com.mygitgor.ecommerce_multivendor.domain.model.Deal;
import com.mygitgor.ecommerce_multivendor.domain.repository.DealRepository;
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
    private final DealRepository dealRepository;

    @Override
    public List<Deal> getDeals() {
        return dealRepository.findAll();
    }

    @Override
    public Deal createDeal(Deal deal) {
        HomeCategoryEntity category = homeCategoryRepository.findById(deal.getCategory().getId()).orElse(null);
        if (category == null) {
            throw new RuntimeException("Category not found");
        }

        deal.setCategory(category);
        return dealRepository.save(deal);
    }

    @Override
    public Deal updateDeal(Deal deal, Long id) throws Exception {
        Deal existDeal = dealRepository.findById(id).orElse(null);
        HomeCategoryEntity category = homeCategoryRepository.findById(deal.getCategory().getId()).orElse(null);

        if (existDeal != null) {
            if (deal.getDiscount() != null) {
                existDeal.setDiscount(deal.getDiscount());
            }
            if (category != null) {
                existDeal.setCategory(category);
            }
            return dealRepository.save(existDeal);
        }
        throw new Exception("Deal not found");
    }

    @Override
    public void deleteDeal(Long id) throws Exception {
        Deal deal = dealRepository.findById(id)
                .orElseThrow(() -> new Exception("Deal not found"));
        dealRepository.delete(deal);
    }
}
