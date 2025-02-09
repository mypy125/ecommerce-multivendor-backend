package com.mygitgor.ecommerce_multivendor.service.impl;

import com.mygitgor.ecommerce_multivendor.domain.HomeCategory;
import com.mygitgor.ecommerce_multivendor.repository.HomeCategoryRepository;
import com.mygitgor.ecommerce_multivendor.service.HomeCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HomeCategoryServiceImpl implements HomeCategoryService {
    private final HomeCategoryRepository homeCategoryRepository;

    @Override
    public HomeCategory createHomeCategory(HomeCategory homeCategory) {
        return null;
    }

    @Override
    public List<HomeCategory> createCategories(List<HomeCategory> homeCategories) {
        return List.of();
    }

    @Override
    public HomeCategory updateHomeCategory(HomeCategory homeCategory, Long id) {
        return null;
    }

    @Override
    public List<HomeCategory> getAllHomeCategories() {
        return List.of();
    }
}
