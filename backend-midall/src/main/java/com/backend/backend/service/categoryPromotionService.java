package com.backend.backend.service;

import com.backend.backend.domain.Category;
import com.backend.backend.domain.Product;
import com.backend.backend.domain.categoryPromotion;
import com.backend.backend.dto.ProductDTO;
import com.backend.backend.dto.categoryPromotionDTO;
import com.backend.backend.exceptions.BadRequestException;
import com.backend.backend.repository.CategoryRepository;
import com.backend.backend.repository.categoryPromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class categoryPromotionService {

    @Autowired
    categoryPromotionRepository catPromoRep;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;

    public categoryPromotion insert(categoryPromotionDTO objDto){
        this.validateDTOCategories(objDto);
        objDto.setId(null);

        return catPromoRep.save(this.fromDTO(objDto));
    }

    private void validateDTOCategories(categoryPromotionDTO categoryPromotionDTO) {
        categoryPromotionDTO.getCategoriesPromotions().forEach(givenCategory -> {
            Category category = categoryService.find(givenCategory.getId());
            if(givenCategory.getName() != null && !Objects.equals(givenCategory.getName(), category.getName()))
                throw new BadRequestException("Category name doesn't match with the given id");
        });
    }

    private categoryPromotion fromDTO(categoryPromotionDTO categoryPromotionDTO) {
        categoryPromotion catpromo = new categoryPromotion(categoryPromotionDTO.getId(),categoryPromotionDTO.getDiscount());

        catpromo.getCategoriesPromotions().addAll(categoryPromotionDTO.getCategoriesPromotions().stream().map(
                categoryDTO -> new Category(categoryDTO.getId(), categoryDTO.getName())
        ).collect(Collectors.toList()));

        return catpromo;
    }

}
