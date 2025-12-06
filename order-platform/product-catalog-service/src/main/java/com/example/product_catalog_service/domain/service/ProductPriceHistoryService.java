package com.example.product_catalog_service.domain.service;

import com.example.product_catalog_service.infrastructure.entity.ProductEntity;
import com.example.product_catalog_service.infrastructure.entity.ProductPriceHistoryEntity;
import com.example.product_catalog_service.infrastructure.repository.ProductPriceHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductPriceHistoryService {

        private final ProductPriceHistoryRepository historyRepository;

        public void recordPriceChange(ProductEntity product,
                                      BigDecimal oldPrice,
                                      BigDecimal newPrice) {

            ProductPriceHistoryEntity history = new ProductPriceHistoryEntity();
            history.setProduct(product);
            history.setOldPrice(oldPrice);
            history.setNewPrice(newPrice);
            history.setChangedAt(new Timestamp(System.currentTimeMillis()));

            historyRepository.save(history);
        }

        public List<ProductPriceHistoryEntity> getHistoryForProduct(Integer productId) {
            return historyRepository.findByProductIdOrderByChangedAtDesc(productId);
        }


}
