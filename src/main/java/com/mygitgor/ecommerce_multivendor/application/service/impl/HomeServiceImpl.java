package com.mygitgor.ecommerce_multivendor.application.service.impl;

import com.mygitgor.ecommerce_multivendor.domain.model.Deal;
import com.mygitgor.ecommerce_multivendor.domain.model.HomeCategory;
import com.mygitgor.ecommerce_multivendor.domain.repository.DealRepository;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.DealEntity;
import com.mygitgor.ecommerce_multivendor.domain.model.Home;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.HomeCategoryEntity;
import com.mygitgor.ecommerce_multivendor.domain.model.costant.HomeCategorySection;
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
    private final DealRepository dealRepository;

    @Override
    public Home createHomePageData(List<HomeCategory> allCategories) {

        List<HomeCategory> gridCategories = allCategories.stream()
               .filter(category -> category.getSection() == HomeCategorySection.GRID)
               .toList();

        List<HomeCategory> shopByCategories = allCategories.stream()
               .filter(category -> category.getSection() == HomeCategorySection.SHOP_BY_CATEGORIES)
               .toList();

        List<HomeCategory> electronicCategories = allCategories.stream()
               .filter(category -> category.getSection() == HomeCategorySection.ELECTRIC_CATEGORIES)
               .toList();

        List<HomeCategory> dealCategories = allCategories.stream()
                .filter(category -> category.getSection() == HomeCategorySection.DEALS)
                .toList();

        List<Deal> createDeals = new ArrayList<>();
        if (dealRepository.findAll().isEmpty()) {
            List<Deal> deals = allCategories.stream()
                    .filter(category -> category.getSection() == HomeCategorySection.DEALS)
                    .map(category -> new Deal(10, category))
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
