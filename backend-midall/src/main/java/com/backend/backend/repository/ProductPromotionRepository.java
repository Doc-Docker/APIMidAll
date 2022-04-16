package com.backend.backend.repository;

import com.backend.backend.domain.ProductPromotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPromotionRepository extends JpaRepository<ProductPromotion, Integer> {
}
