package com.mygitgor.ecommerce_multivendor.domain;

import com.mygitgor.ecommerce_multivendor.domain.abstraction.BaseProductEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Product extends BaseProductEntity {
    private int mrpPrice;
    private int sellingPrice;
    private int discountPercent;

    private String color;

    @ElementCollection
    private List<String> images = new ArrayList<>();

    private int numRatings;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Seller seller;

//    @ElementCollection
    private String size;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review>reviews = new ArrayList<>();
}
