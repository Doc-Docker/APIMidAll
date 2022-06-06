package com.backend.backend.enumerate;

import java.time.LocalDate;
import java.util.List;

import com.backend.backend.dto.ProductDTO;
import com.backend.backend.dto.ProductPromotionDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum ReceivePromotion {
	PRODUCT(1), TOTAL(2), PRODUCT_QUANTITY(3), CATEGORY(4);

	private int code;

	private ReceivePromotion(int code) {
		this.code = code;    
	}

	public int getCode() {
		return code;
	}

	public static ReceivePromotion valueOf(int code) {
		for (ReceivePromotion value : ReceivePromotion.values()) {
			if (code == value.getCode()) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid Type promotion code");
	}
	
	
}