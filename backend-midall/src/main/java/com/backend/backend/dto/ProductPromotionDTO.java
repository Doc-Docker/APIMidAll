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

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor

public class ProductPromotionDTO implements Serializable {

	private static final long serialVerionUID = 1L;

	private Integer id;

	@NotEmpty(message = "{field.name.mandatory}")
	private String name;

	private Boolean isActive;
	
	private Integer idCategory;

	private TypePromotion typePromotion;
	
	private ReceivePromotion receivePromotion;

	@Min(value = 0L, message = "Quantidade value must be positive")
    private Integer quantidade;
	
	@Min(value = 0L, message = "Quantidade value must be positive")
	private Integer totalCompra;

	@Min(value = 0L, message = "Discount value must be positive")
	private Double discount;

	private List<ProductDTO> products = new ArrayList<>();

	public ProductPromotionDTO(ProductPromotion obj) {
		id = obj.getId();
		name= obj.getName();
		isActive = obj.getIsActive();
		idCategory = obj.getIdCategory();
		typePromotion = obj.getTypePromotion();
		receivePromotion = obj.getReceivePromotion();
		quantidade = obj.getQuantidade();
		totalCompra = obj.getTotalCompra();
		discount = obj.getDiscount();
		
	}

}
