package com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.HomeCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeCategoryJpaRepository extends JpaRepository<HomeCategoryEntity,Long> {
}
