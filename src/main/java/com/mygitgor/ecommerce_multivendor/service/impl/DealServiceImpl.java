package com.mygitgor.ecommerce_multivendor.service.impl;

import com.mygitgor.ecommerce_multivendor.domain.Deal;
import com.mygitgor.ecommerce_multivendor.repository.DealRepository;
import com.mygitgor.ecommerce_multivendor.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DealServiceImpl implements DealService {
    private final DealRepository dealRepository;
    
    @Override
    public List<Deal> getDeals() {
        return List.of();
    }

    @Override
    public Deal createDeal(Deal deal) {
        return null;
    }

    @Override
    public Deal updateDeal(Deal deal) {
        return null;
    }

    @Override
    public void deleteDeal(Deal deal) {

    }
}
