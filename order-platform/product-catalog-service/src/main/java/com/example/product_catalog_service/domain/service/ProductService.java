package com.example.product_catalog_service.domain.service;

import com.example.product_catalog_service.api.dto.CreateProductRequest;
import com.example.product_catalog_service.api.dto.ProductResponse;
import com.example.product_catalog_service.api.dto.UpdateProductRequest;
import com.example.product_catalog_service.api.mapper.ProductMapper;
import com.example.product_catalog_service.infrastructure.entity.CategoryEntity;
import com.example.product_catalog_service.infrastructure.entity.ProductEntity;
import com.example.product_catalog_service.infrastructure.entity.ProductPriceHistoryEntity;
import com.example.product_catalog_service.infrastructure.repository.CategoryRepository;
import com.example.product_catalog_service.infrastructure.repository.ProductPriceHistoryRepository;
import com.example.product_catalog_service.infrastructure.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductPriceHistoryService priceHistoryService;
    private final ProductMapper productMapper;

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

    @Transactional
    public ProductEntity updatePrice(Integer productId, BigDecimal newPrice) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        BigDecimal oldPrice = product.getPrice();
        if (oldPrice != null && oldPrice.compareTo(newPrice) == 0) {
            // no change, nothing to do
            return product;
        }

        product.setPrice(newPrice);
        product.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        productRepository.save(product);

        // record history via service (not from controller)
        priceHistoryService.recordPriceChange(product, oldPrice, newPrice);

        return product;
    }


    @Transactional
    public ProductResponse createProduct(CreateProductRequest request) {
        CategoryEntity category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        ProductEntity product = new ProductEntity();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setCategory(category);
        product.setCreateAt(new Timestamp(System.currentTimeMillis()));
        product.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        product.setDelete(false);

        ProductEntity saved = productRepository.save(product);
        return productMapper.toResponse(saved);
    }

    @Transactional
    public ProductResponse updateProduct(Integer id, UpdateProductRequest request) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (request.getName() != null) {
            product.setName(request.getName());
        }
        if (request.getDescription() != null) {
            product.setDescription(request.getDescription());
        }
        if (request.getStock() != null) {
            product.setStock(request.getStock());
        }
        if (request.getCategoryId() != null) {
            CategoryEntity category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);
        }

        product.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        ProductEntity saved = productRepository.save(product);
        return productMapper.toResponse(saved);
    }

    @Transactional
    public void softDeleteProduct(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        productRepository.softDelete(id);
    }

    @Transactional
    public ProductResponse updatePriceAndReturnDto(Integer productId, BigDecimal newPrice) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        BigDecimal oldPrice = product.getPrice();
        if (oldPrice != null && oldPrice.compareTo(newPrice) == 0) {
            return productMapper.toResponse(product);
        }

        product.setPrice(newPrice);
        product.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        productRepository.save(product);

        priceHistoryService.recordPriceChange(product, oldPrice, newPrice);

        return productMapper.toResponse(product);
    }

}
