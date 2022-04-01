package com.backend.backend.dto;

import com.backend.backend.domain.Category;
import com.backend.backend.domain.categoryPromotion;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class categoryPromotionDTO implements Serializable {

    private static final long serialVerionUID = 1L;

    private Integer id;

    private Double discount;

    private List<CategoryDTO> categoriesPromotions = new ArrayList<>();

    public categoryPromotionDTO(categoryPromotion obj){
        id = obj.getId();
        discount = obj.getDiscount();
    }

}
