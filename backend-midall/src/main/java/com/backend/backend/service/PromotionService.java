package com.backend.backend.service;

import com.backend.backend.domain.Category;
import com.backend.backend.domain.CategoryPromotion;
import com.backend.backend.dto.CategoryPromotionDTO;
import com.backend.backend.exceptions.BadRequestException;
import com.backend.backend.repository.CategoryRepository;
import com.backend.backend.repository.CategoryPromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PromotionService {

    //@Autowired
    //PromotionRepository promoRep;

    //@Autowired
    //PromotionService promotionService;

    //@Autowired
    //PromotionRepository promotionRepository;

    //public Promotion insert(PromotionDTO objDto){
      //  this.validateDTOCategories(objDto);
        //objDto.setId(null);

        //return promoRep.save(this.fromDTO(objDto));
        //return catPromoRep.saveAndFlush(this.fromDTO(objDto));
    //}

    //public Promotion find(Integer id){
      //  Optional<Promotion> promo = PromoRep.findById(id);
        //return cat.orElseThrow();
    //}

    //private void validateDTOPromotion(PromotionDTO promotionDTO) {
      //  promotionDTO.getPromotions().forEach(givenPromotion -> {
        //    Promotion promotion = promotionService.find(givenPromotion.getId());
        //});
    //}

    //private Promotion fromDTO(PromotionDTO promotionDTO) {
      //  Promotion promon = new Promotion(promotionDTO.getId(),promotionDTO.getDiscount());

        //promon.getPromotions().addAll(promotionDTO.getDiscounts().stream().map(
          //      promotionDTO -> new Promotion(promotionDTO.getId(), categoryDTO.getPrice())
        //).collect(Collectors.toList()));
        //return promon;
    //}

}
