package com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mygitgor.ecommerce_multivendor.infrastructure.database.abstraction.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "review")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReviewEntity extends BaseEntity {

    @Column(nullable = false)
    private String reviewText;

    @Column(nullable = false)
    private double rating;

    @ElementCollection
    private List<String> productImages;

    @JsonIgnore
    @ManyToOne
    private ProductEntity product;

    @ManyToOne
    private UserEntity user;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
