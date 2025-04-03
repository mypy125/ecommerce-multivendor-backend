package com.mygitgor.ecommerce_multivendor.domain.model;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.DealEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.HomeCategoryEntity;
import lombok.*;

import java.util.List;

@Data
public class Home {
    private List<HomeCategory>grid;
    private List<HomeCategory>shopByCategories;
    private List<HomeCategory>electronicCategories;
    private List<HomeCategory>dealCategories;
    private List<Deal>deals;
}
