package com.backend.backend.enumerate;

import java.time.LocalDate;
import java.util.List;

import com.backend.backend.dto.ProductDTO;
import com.backend.backend.dto.ProductPromotionDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum TypePromotion {
	VALUE(1), PERCENTAGE(2), THE_AMOUNT(3), VALUE_PERCENTAGE(4), PERCENTAGE_THE_AMOUNT(5), THE_AMOUNT_VALUE(6);

	private int code;

	private TypePromotion(int code) {
		this.code = code;    
	}

	public int getCode() {
		return code;
	}

	public static TypePromotion valueOf(int code) {
		for (TypePromotion value : TypePromotion.values()) {
			if (code == value.getCode()) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid Type promotion code");
	}
	
	
	
}