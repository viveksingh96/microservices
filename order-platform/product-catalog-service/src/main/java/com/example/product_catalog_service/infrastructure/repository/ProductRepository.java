package com.example.product_catalog_service.infrastructure.repository;

import com.example.product_catalog_service.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {
}
