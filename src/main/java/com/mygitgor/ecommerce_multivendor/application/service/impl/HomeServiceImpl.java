package com.mygitgor.ecommerce_multivendor.application.service.impl;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.DealEntity;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.Home;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.HomeCategoryEntity;
import com.mygitgor.ecommerce_multivendor.domain.costant.HomeCategorySection;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa.DealJpaRepository;
import com.mygitgor.ecommerce_multivendor.application.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {
    private final DealJpaRepository dealRepository;

    @Override
    public Home createHomePageData(List<HomeCategoryEntity> allCategories) {

        List<HomeCategoryEntity> gridCategories = allCategories.stream()
               .filter(category -> category.getSection() == HomeCategorySection.GRID)
               .toList();

        List<HomeCategoryEntity> shopByCategories = allCategories.stream()
               .filter(category -> category.getSection() == HomeCategorySection.SHOP_BY_CATEGORIES)
               .toList();

        List<HomeCategoryEntity> electronicCategories = allCategories.stream()
               .filter(category -> category.getSection() == HomeCategorySection.ELECTRIC_CATEGORIES)
               .toList();

        List<HomeCategoryEntity> dealCategories = allCategories.stream()
                .filter(category -> category.getSection() == HomeCategorySection.DEALS)
                .toList();

        List<DealEntity> createDeals = new ArrayList<>();
        if (dealRepository.findAll().isEmpty()) {
            List<DealEntity> deals = allCategories.stream()
                    .filter(category -> category.getSection() == HomeCategorySection.DEALS)
                    .map(category -> new DealEntity(10, category))
                    .collect(Collectors.toList());

            createDeals = dealRepository.saveAll(deals);
        } else {
            createDeals = dealRepository.findAll();
        }

        Home home = new Home();
        home.setGrid(gridCategories);
        home.setShopByCategories(shopByCategories);
        home.setElectronicCategories(electronicCategories);
        home.setDeals(createDeals);
        home.setDealCategories(dealCategories);

        return home;
    }
}
