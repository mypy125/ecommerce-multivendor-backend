package com.mygitgor.ecommerce_multivendor.domain.model;

import com.mygitgor.ecommerce_multivendor.domain.model.abstraction.BaseProduct;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseProduct {
    private int mrpPrice;
    private int sellingPrice;
    private int discountPercent;
    private String color;
    private List<String> images = new ArrayList<>();
    private int numRatings;
    private Category category;
    private Seller seller;
    private String size;
    private List<Review> reviews = new ArrayList<>();
}
