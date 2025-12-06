package com.example.product_catalog_service.api.controller;

import com.example.product_catalog_service.domain.service.ProductPriceHistoryService;
import com.example.product_catalog_service.domain.service.ProductService;
import com.example.product_catalog_service.infrastructure.entity.ProductEntity;
import com.example.product_catalog_service.infrastructure.entity.ProductPriceHistoryEntity;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductPriceHistoryService priceHistoryService;

    @GetMapping
    public Page<ProductEntity> search(@RequestParam(defaultValue = "") String query
            , @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return productService.searchProducts(query, page, size);
    }

    @GetMapping("/{id}")
    public ProductEntity get(@PathVariable Integer id) {
        return productService.getById(id);
    }

    @GetMapping("/category/{id}")
    public List<ProductEntity> productByCategory(@PathVariable Integer id) {
        return productService.getProductsByCategory(id);
    }

    @PutMapping("/{id}/price")
    public ProductEntity updatePrice(
            @PathVariable Integer id,
            @RequestParam BigDecimal newPrice
    ) {
return productService.updatePrice(id, newPrice);

    }

    @GetMapping("/{id}/price/history")
    public List<ProductPriceHistoryEntity> getHistory(
            @PathVariable Integer id
    )
    {
        return priceHistoryService.getHistoryForProduct(id);
    }

}
