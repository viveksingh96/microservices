package com.example.product_catalog_service.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateProductRequest {

    private String name;

    private String description;

    @Positive
    private BigDecimal price;

    @Min(0)
    private Integer stock;

    @Positive
    private Integer categoryId;
}
