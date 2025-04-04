package com.mygitgor.ecommerce_multivendor.domain.repository;

import com.mygitgor.ecommerce_multivendor.domain.model.Category;

public interface CategoryRepository {
    Category findByCategoryId(String category);
    Category save(Category category);
}
