package com.backend.backend.dto;

import com.backend.backend.domain.CategoryPromotion;
import com.backend.backend.domain.ProductPromotion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductPromotionDTO implements Serializable {

    private static final long serialVerionUID = 1L;

    private Integer id;

    private Double discount;

    private List<ProductDTO> products = new ArrayList<>();

    public ProductPromotionDTO(ProductPromotion obj){
        id = obj.getId();
        discount = obj.getDiscount();
    }

}
