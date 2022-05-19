package com.backend.backend.service;

import com.backend.backend.domain.Category;
import com.backend.backend.domain.CategoryPromotion;
import com.backend.backend.dto.CategoryDTO;
import com.backend.backend.dto.CategoryPromotionDTO;
import com.backend.backend.exceptions.BadRequestException;
import com.backend.backend.repository.CategoryRepository;
import com.backend.backend.repository.CategoryPromotionRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryPromotionService {

    @Autowired
    CategoryPromotionRepository catPromoRep;

    @Autowired
    CategoryService categoryService;
    
    @Autowired
    CategoryRepository categoryRepository;

    public CategoryPromotion insert(CategoryPromotionDTO objDto){
        this.validateDTOCategories(objDto);
        objDto.setId(null);

        return catPromoRep.save(this.fromDTO(objDto));
        //return catPromoRep.saveAndFlush(this.fromDTO(objDto));
    }

    public CategoryPromotion find(Integer id){
        Optional<CategoryPromotion> cat = catPromoRep.findById(id);
        return cat.orElseThrow();
    }

    private void validateDTOCategories(CategoryPromotionDTO categoryPromotionDTO) {
        categoryPromotionDTO.getCategories().forEach(givenCategory -> {
            Category category = categoryService.find(givenCategory.getId());
            if(givenCategory.getName() != null && !Objects.equals(givenCategory.getName(), category.getName()))
                throw new BadRequestException("Category name doesn't match with the given id");
        });
    }

    private CategoryPromotion fromDTO(CategoryPromotionDTO categoryPromotionDTO) {
        CategoryPromotion catpromo = new CategoryPromotion(categoryPromotionDTO.getId(),categoryPromotionDTO.getDiscount());
        
        catpromo.getCategories().addAll(categoryPromotionDTO.getCategories().stream().map(
              categoryDTO -> new Category (categoryDTO.getId(), categoryDTO.getName())
      ).collect(Collectors.toList()));


        return catpromo;
    }

    
}
