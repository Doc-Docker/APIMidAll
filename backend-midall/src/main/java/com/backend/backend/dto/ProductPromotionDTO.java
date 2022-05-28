package com.backend.backend.dto;

import com.backend.backend.domain.CategoryPromotion;
import com.backend.backend.domain.ProductPromotion;
import com.backend.backend.enumerate.ReceivePromotion;
import com.backend.backend.enumerate.TypePromotion;

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
	
	private Integer numberCategory;
	
	private String name;
	
	private Boolean isActive;

	private TypePromotion typePromotion;
	
	private ReceivePromotion receivePromotion;
	
    private Integer quantidade;

	private Double discount;

	private List<ProductDTO> products = new ArrayList<>();

	public ProductPromotionDTO(ProductPromotion obj) {
		id = obj.getId();
		name= obj.getName();
		isActive = obj.getIsActive();
		typePromotion = obj.getTypePromotion();
		receivePromotion = obj.getReceivePromition();
		quantidade = obj.getQuantidade();
		discount = obj.getDiscount();
		
	}

}
