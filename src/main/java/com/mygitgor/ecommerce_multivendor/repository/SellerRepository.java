package com.mygitgor.ecommerce_multivendor.repository;

import com.mygitgor.ecommerce_multivendor.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller findByEmail(String email);
}
