package com.example.product_catalog_service.infrastructure.repository;

import com.example.product_catalog_service.infrastructure.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Integer> {
}
