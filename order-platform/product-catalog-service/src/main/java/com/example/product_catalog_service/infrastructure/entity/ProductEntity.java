package com.example.product_catalog_service.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name="ProductEntity.findExpensiveProducts",
query="SELECT p from ProductEntity p WHERE p.price > :price")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;

    @ManyToOne
    @JoinColumn(name="category_id")
    private CategoryEntity category;

    private Timestamp createAt;
    private Timestamp updatedAt;
}
