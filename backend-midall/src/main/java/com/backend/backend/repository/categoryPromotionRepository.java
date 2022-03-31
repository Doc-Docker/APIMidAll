package com.backend.backend.repository;

import com.backend.backend.domain.categoryPromotion;
import com.backend.backend.domain.productPromotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface categoryPromotionRepository extends JpaRepository<categoryPromotion, Integer> {
}
