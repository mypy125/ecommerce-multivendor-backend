package com.mygitgor.ecommerce_multivendor.domain.repository;

import com.mygitgor.ecommerce_multivendor.domain.model.HomeCategory;

import java.util.List;
import java.util.Optional;

public interface HomeCategoryRepository {
    HomeCategory save(HomeCategory homeCategory);
    List<HomeCategory> saveAll(List<HomeCategory> homeCategories);
    List<HomeCategory> findAll();
    Optional<HomeCategory> findById(Long id);
}
