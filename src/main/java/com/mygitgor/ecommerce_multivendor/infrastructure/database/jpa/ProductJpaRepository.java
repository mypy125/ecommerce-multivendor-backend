package com.mygitgor.ecommerce_multivendor.infrastructure.database.jpa;

import com.mygitgor.ecommerce_multivendor.infrastructure.database.entitiy.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity,Long>, JpaSpecificationExecutor<ProductEntity> {
    List<ProductEntity> findBySellerId(Long sellerId);

    @Query("SELECT p FROM ProductEntity p WHERE " +
            "(:query IS NULL OR LOWER(p.title) LIKE LOWER(CONCAT('%', :query, '%'))) " +
            "OR (:query IS NULL OR LOWER(p.category.name) LIKE LOWER(CONCAT('%', :query, '%')))")
    List<ProductEntity> searchProduct(@Param("query") String query);

}
