package com.example.product_catalog_service.api.controller;

import com.example.product_catalog_service.api.dto.CreateProductRequest;
import com.example.product_catalog_service.api.dto.ProductResponse;
import com.example.product_catalog_service.api.dto.UpdateProductRequest;
import com.example.product_catalog_service.domain.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class ProductAdminController {
    private final ProductService productService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse create(@Valid @RequestBody CreateProductRequest request) {
        return productService.createProduct(request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse update(@PathVariable Integer id,
                                  @Valid @RequestBody UpdateProductRequest request) {
        return productService.updateProduct(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Integer id) {
        productService.softDeleteProduct(id);
    }

    @PutMapping("/{id}/price")
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse updatePrice(@PathVariable Integer id,
                                       @RequestParam BigDecimal newPrice) {
        return productService.updatePriceAndReturnDto(id, newPrice);
    }
}
