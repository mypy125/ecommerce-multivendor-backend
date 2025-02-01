package com.mygitgor.ecommerce_multivendor.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mygitgor.ecommerce_multivendor.domain.abstraction.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Review extends BaseEntity {

    @Column(nullable = false)
    private String reviewText;

    @Column(nullable = false)
    private double rating;

    @ElementCollection
    private List<String> productImages;

    @JsonIgnore
    @ManyToOne
    private Product product;

    @ManyToOne
    private Users users;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
