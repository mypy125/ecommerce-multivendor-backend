package com.mygitgor.ecommerce_multivendor.service;

import com.mygitgor.ecommerce_multivendor.domain.Deal;

import java.util.List;

public interface DealService {
    List<Deal> getDeals();
    Deal createDeal(Deal deal);
    Deal updateDeal(Deal deal);
    void deleteDeal(Deal deal);
}
