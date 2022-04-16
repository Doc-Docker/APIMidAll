package com.backend.backend.dto;

import com.backend.backend.domain.Category;
import com.backend.backend.domain.CategoryPromotion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO implements Serializable {

    private static final long serialVerionUID = 1L;

    private Integer id;

    private String name;

    private List<CategoryPromotion> catPromotions = new ArrayList<>();

    public CategoryDTO(Category obj){
        id = obj.getId();
        name = obj.getName();
    }

}
