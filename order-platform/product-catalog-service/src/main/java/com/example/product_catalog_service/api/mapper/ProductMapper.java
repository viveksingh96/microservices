package com.example.product_catalog_service.api.mapper;

import com.example.product_catalog_service.api.dto.ProductResponse;
import com.example.product_catalog_service.infrastructure.entity.ProductEntity;

public class ProductMapper {

    public ProductResponse toResponse(ProductEntity entity) {
        if (entity == null) return null;

        return ProductResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .categoryName(
                        entity.getCategory() != null ? entity.getCategory().getName() : null
                )
                .build();
    }
}
