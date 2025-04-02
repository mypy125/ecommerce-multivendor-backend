package com.mygitgor.ecommerce_multivendor.application.service.impl;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.HomeCategoryEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.HomeCategoryJpaRepository;
import com.mygitgor.ecommerce_multivendor.application.service.HomeCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HomeCategoryServiceImpl implements HomeCategoryService {
    private final HomeCategoryJpaRepository homeCategoryRepository;

    @Override
    public HomeCategoryEntity createHomeCategory(HomeCategoryEntity homeCategory) {
        return homeCategoryRepository.save(homeCategory);
    }

    @Override
    public List<HomeCategoryEntity> createCategories(List<HomeCategoryEntity> homeCategories) {
        if(homeCategoryRepository.findAll().isEmpty()){
            return homeCategoryRepository.saveAll(homeCategories);
        }
        return homeCategoryRepository.findAll();
    }

    @Override
    public HomeCategoryEntity updateHomeCategory(HomeCategoryEntity homeCategory, Long id) throws Exception {
        HomeCategoryEntity existCategory = homeCategoryRepository.findById(id)
                .orElseThrow(()->new Exception("category not found"));

        if(homeCategory.getImage()!=null){
            existCategory.setImage(homeCategory.getImage());
        }
        if(homeCategory.getCategoryId()!=null){
            existCategory.setCategoryId(homeCategory.getCategoryId());
        }
        return homeCategoryRepository.save(existCategory);
    }

    @Override
    public List<HomeCategoryEntity> getAllHomeCategories() {
        return homeCategoryRepository.findAll();
    }
}
