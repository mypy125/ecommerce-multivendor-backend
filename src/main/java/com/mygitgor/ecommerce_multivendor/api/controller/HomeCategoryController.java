package com.mygitgor.ecommerce_multivendor.api.controller;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.Home;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.HomeCategoryEntity;
import com.mygitgor.ecommerce_multivendor.application.service.HomeCategoryService;
import com.mygitgor.ecommerce_multivendor.application.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeCategoryController {
    private final HomeCategoryService homeCategoryService;
    private final HomeService homeService;

    @PostMapping("/home/categories")
    public ResponseEntity<Home>createHomeCategory(@RequestBody List<HomeCategoryEntity> homeCategories)
    {
        List<HomeCategoryEntity>categories = homeCategoryService.createCategories(homeCategories);
        Home home = homeService.createHomePageData(categories);
        return new ResponseEntity<>(home, HttpStatus.ACCEPTED);
    }

    @GetMapping("/admin/home-category")
    public ResponseEntity<List<HomeCategoryEntity>>getHomeCategory()
    {
        List<HomeCategoryEntity>categories = homeCategoryService.getAllHomeCategories();
        return ResponseEntity.ok(categories);
    }

    @PatchMapping("/admin/home-category/{id}")
    public ResponseEntity<HomeCategoryEntity>updateHomeCategory(@PathVariable Long id,
                                                                @RequestBody HomeCategoryEntity homeCategory) throws Exception
    {
        HomeCategoryEntity updateHomeCategory = homeCategoryService.updateHomeCategory(homeCategory,id);
        return ResponseEntity.ok(updateHomeCategory);
    }
}
