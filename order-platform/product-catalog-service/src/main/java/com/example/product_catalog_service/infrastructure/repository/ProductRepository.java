package com.example.product_catalog_service.infrastructure.repository;

import com.example.product_catalog_service.infrastructure.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {
    // Derived query (find all products of one category)
    List<ProductEntity> findByCategoryId(Integer categoryId);
    // Derived + sorting
    List<ProductEntity> findByCategoryId(Integer categoryId, Sort sort);
    // Pagination + search
    Page<ProductEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
    // NamedQuery usage
    List<ProductEntity> findExpensiveProducts(@Param("price")BigDecimal price);

    // JPQL query
    @Query("SELECT p FROM ProductEntity p WHERE p.stock < :threshold")
    List<ProductEntity> findLowStockProducts(@Param("threshold") Integer threshold);

    // Native query
    @Query(value = "SELECT * FROM product WHERE price BETWEEN :min AND :max", nativeQuery = true)
    List<ProductEntity> findProductsByPriceRange(@Param("min") BigDecimal min,
                                                 @Param("max") BigDecimal max);
}


