package com.mygitgor.ecommerce_multivendor.domain;

import com.mygitgor.ecommerce_multivendor.domain.abstraction.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Wishlist extends BaseEntity {

    @OneToOne
    private Users users;

    @ManyToMany
    private Set<Product> products = new HashSet<>();
}
