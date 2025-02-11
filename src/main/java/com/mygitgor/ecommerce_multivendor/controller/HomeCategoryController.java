package com.mygitgor.ecommerce_multivendor.controller;

import com.mygitgor.ecommerce_multivendor.domain.Home;
import com.mygitgor.ecommerce_multivendor.domain.HomeCategory;
import com.mygitgor.ecommerce_multivendor.service.HomeCategoryService;
import com.mygitgor.ecommerce_multivendor.service.HomeService;
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
    public ResponseEntity<Home>createHomeCategory(@RequestBody List<HomeCategory> homeCategories)
    {
        List<HomeCategory>categories = homeCategoryService.createCategories(homeCategories);
        Home home = homeService.createHomePageData(categories);
        return new ResponseEntity<>(home, HttpStatus.ACCEPTED);
    }

    @GetMapping("/admin/home-category")
    public ResponseEntity<List<HomeCategory>>getHomeCategory()
    {
        List<HomeCategory>categories = homeCategoryService.getAllHomeCategories();
        return ResponseEntity.ok(categories);
    }

    @PatchMapping("/admin/home-category/{id}")
    public ResponseEntity<HomeCategory>updateHomeCategory(@PathVariable Long id,
                                                          @RequestBody HomeCategory homeCategory) throws Exception
    {
        HomeCategory updateHomeCategory = homeCategoryService.updateHomeCategory(homeCategory,id);
        return ResponseEntity.ok(updateHomeCategory);
    }
}
