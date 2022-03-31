package com.backend.backend.service;

import com.backend.backend.domain.Category;
import com.backend.backend.domain.categoryPromotion;
import com.backend.backend.dto.categoryPromotionDTO;
import com.backend.backend.repository.categoryPromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class categoryPromotionService {

    @Autowired
    categoryPromotionRepository rep;

    public categoryPromotion insert(categoryPromotionDTO objDto){
        objDto.setId(null);

        Optional<categoryPromotion> catQueryReturn = rep.findById(objDto.getCategory_id());

        if(catQueryReturn.isEmpty()){
            throw new RuntimeException("Category does not exist");
        }
        categoryPromotion obj = new categoryPromotion(objDto.getId(), objDto.getDiscount(), objDto.getCategory_id());

        return rep.save(obj);
    }

}
