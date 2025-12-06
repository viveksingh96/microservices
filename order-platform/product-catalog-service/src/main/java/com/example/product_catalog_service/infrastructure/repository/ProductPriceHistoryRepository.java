package com.example.product_catalog_service.infrastructure.repository;

import com.example.product_catalog_service.infrastructure.entity.ProductPriceHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface ProductPriceHistoryRepository extends JpaRepository<ProductPriceHistoryEntity,Integer> {
    List<ProductPriceHistoryEntity> findByProductIdOrderByChangedAtDesc(Integer productId);

    @Query("SELECT h FROM ProductPriceHistoryEntity h WHERE h.changedAt BETWEEN :start AND :end")
    List<ProductPriceHistoryEntity> findBetweenDates(
            @Param("start")Timestamp start,
            @Param("end") Timestamp end
            );
}
