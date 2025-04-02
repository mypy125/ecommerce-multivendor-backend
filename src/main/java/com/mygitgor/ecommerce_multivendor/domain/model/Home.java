package com.mygitgor.ecommerce_multivendor.domain.model;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.DealEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.HomeCategoryEntity;
import lombok.*;

import java.util.List;

@Data
public class Home {
    private List<HomeCategoryEntity>grid;
    private List<HomeCategoryEntity>shopByCategories;
    private List<HomeCategoryEntity>electronicCategories;
    private List<HomeCategoryEntity>dealCategories;
    private List<DealEntity>deals;
}
