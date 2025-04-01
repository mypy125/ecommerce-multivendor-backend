package com.mygitgor.ecommerce_multivendor.application.service;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.Home;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.HomeCategoryEntity;

import java.util.List;

public interface HomeService {
    Home createHomePageData(List<HomeCategoryEntity> allCategory);
}
