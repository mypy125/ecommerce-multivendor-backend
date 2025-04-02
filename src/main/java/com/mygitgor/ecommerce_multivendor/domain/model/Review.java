package com.mygitgor.ecommerce_multivendor.domain.model;

import com.mygitgor.ecommerce_multivendor.domain.model.abstraction.BaseModelId;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review extends BaseModelId {
    private String reviewText;
    private double rating;
    private List<String> productImages;
    private Product product;
    private User user;
    private LocalDateTime createdAt = LocalDateTime.now();
}
