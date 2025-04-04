package com.mygitgor.ecommerce_multivendor.application.impl;

import com.mygitgor.ecommerce_multivendor.domain.model.HomeCategory;
import com.mygitgor.ecommerce_multivendor.domain.repository.HomeCategoryRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.HomeCategoryEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.HomeCategoryJpaRepository;
import com.mygitgor.ecommerce_multivendor.application.service.HomeCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HomeCategoryServiceImpl implements HomeCategoryService {
    private final HomeCategoryRepository homeCategoryRepository;

    @Override
    public HomeCategory createHomeCategory(HomeCategory homeCategory) {
        return homeCategoryRepository.save(homeCategory);
    }

    @Override
    public List<HomeCategory> createCategories(List<HomeCategory> homeCategories) {
        if(homeCategoryRepository.findAll().isEmpty()){
            return homeCategoryRepository.saveAll(homeCategories);
        }
        return homeCategoryRepository.findAll();
    }

    @Override
    public HomeCategory updateHomeCategory(HomeCategory homeCategory, Long id) throws Exception {
        HomeCategory existCategory = homeCategoryRepository.findById(id)
                .orElseThrow(() -> new Exception("Category not found"));

        if(homeCategory.getImage() != null) {
            existCategory.setImage(homeCategory.getImage());
        }
        if(homeCategory.getCategoryId() != null) {
            existCategory.setCategoryId(homeCategory.getCategoryId());
        }
        return homeCategoryRepository.save(existCategory);
    }

    @Override
    public List<HomeCategory> getAllHomeCategories() {
        return homeCategoryRepository.findAll();
    }
}
