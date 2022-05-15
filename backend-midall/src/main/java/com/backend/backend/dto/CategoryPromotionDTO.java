package com.backend.backend.dto;

import com.backend.backend.domain.CategoryPromotion;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CategoryPromotionDTO implements Serializable {

	private static final long serialVerionUID = 1L;

	private Integer id;

	private Double discount;
	
	private List<CategoryDTO> categories = new ArrayList<>();

	public CategoryPromotionDTO(CategoryPromotion obj) {
		id = obj.getId();
		discount = obj.getDiscount();
	}

}
