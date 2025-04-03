package com.mygitgor.ecommerce_multivendor.domain.repository;

import com.mygitgor.ecommerce_multivendor.domain.model.Deal;

import java.util.List;
import java.util.Optional;

public interface DealRepository {
    List<Deal> findAll();
    Deal save(Deal deal);
    Optional<Deal> findById(Long id);
    void delete(Deal deal);
    List<Deal> saveAll(List<Deal> deal);
}
