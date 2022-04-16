package com.backend.backend.repository;

import com.backend.backend.domain.CategoryPromotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryPromotionRepository extends JpaRepository<CategoryPromotion, Integer> {
}
