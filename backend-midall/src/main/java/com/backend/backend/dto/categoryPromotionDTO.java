package com.backend.backend.dto;

import com.backend.backend.domain.categoryPromotion;
import com.backend.backend.domain.productPromotion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class categoryPromotionDTO implements Serializable {

    private static final long serialVerionUID = 1L;

    private Integer id;

    private Double discount;

    private Integer category_id;

    public categoryPromotionDTO(categoryPromotion obj){
        id = obj.getId();
        discount = obj.getDiscount();
        category_id = obj.getCategory_id();
    }

}
