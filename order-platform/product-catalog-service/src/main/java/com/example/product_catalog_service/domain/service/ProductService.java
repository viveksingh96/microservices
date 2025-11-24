package com.example.product_catalog_service.domain.service;

import com.example.product_catalog_service.infrastructure.entity.ProductEntity;
import com.example.product_catalog_service.infrastructure.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<ProductEntity> searchProducts(String query, int page, int size){
        return productRepository.findByNameContainingIgnoreCase(query, PageRequest.of(
                page,size, Sort.by("price").ascending()
        ));
    }

    public List<ProductEntity> getProductsByCategory(Integer catId) {
        return productRepository.findByCategoryId(catId);
    }

    public ProductEntity getById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
