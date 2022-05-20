package com.backend.backend.dto;

import com.backend.backend.domain.CategoryPromotion;
import com.backend.backend.domain.ProductPromotion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
public class ProductPromotionDTO implements Serializable {

	private static final long serialVerionUID = 1L;

	private Integer id;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate startDate;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate finalDate;

	private Boolean isActive;

	private String typePromotion;

	private Double discount;

	private List<ProductDTO> products = new ArrayList<>();

	public ProductPromotionDTO(ProductPromotion obj) {
		id = obj.getId();
		startDate = obj.getStartDate();
		finalDate = obj.getFinalDate();
		isActive = obj.getIsActive();
		typePromotion = obj.getTypePromotion();
		discount = obj.getDiscount();
		
	}

}
