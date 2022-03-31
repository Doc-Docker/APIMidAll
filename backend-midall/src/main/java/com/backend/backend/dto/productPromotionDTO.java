package com.backend.backend.dto;

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
public class productPromotionDTO implements Serializable {

    private static final long serialVerionUID = 1L;

    private Integer id;

    private Double discount;

    private Integer product_id;

    public productPromotionDTO(productPromotion obj){
        id = obj.getId();
        discount = obj.getDiscount();
        product_id = obj.getProduct_id();
    }

}
