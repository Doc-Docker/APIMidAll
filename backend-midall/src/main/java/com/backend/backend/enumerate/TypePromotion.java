package com.backend.backend.enumerate;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum TypePromotion {
	VALUE(1), PERCENTAGE(2);

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