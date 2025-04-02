package com.mygitgor.ecommerce_multivendor.application.service;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.HomeCategoryEntity;

import java.util.List;

public interface HomeCategoryService {
    HomeCategoryEntity createHomeCategory(HomeCategoryEntity homeCategory);
    List<HomeCategoryEntity>createCategories(List<HomeCategoryEntity> homeCategories);
    HomeCategoryEntity updateHomeCategory(HomeCategoryEntity homeCategory, Long id) throws Exception;
    List<HomeCategoryEntity>getAllHomeCategories();
}
