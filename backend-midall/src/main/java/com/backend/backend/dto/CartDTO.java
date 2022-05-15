package com.backend.backend.dto;

import com.backend.backend.domain.Cart;
import com.backend.backend.domain.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
	private static final long serialVerionUID = 1L;
	
	   private Integer id;

	    private String name;

	    private Double price;

	    private String description;
	    
	    public CartDTO(Cart obj) {
			id = obj.getId();
			name = obj.getName();
			price = obj.getPrice();
			description =obj.getDescription();
		}
}
