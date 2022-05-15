package com.backend.backend.dto;

import com.backend.backend.domain.Product;
import com.backend.backend.domain.ProductPromotion;
import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = { "id" })
public class ProductDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;


	@NotBlank(message = "Name is required")
	private String name;

	@PositiveOrZero(message = "Price must be a value higher or equal to 0")
	private Double price;

	private String description;
	 
	private List<CategoryDTO> categories = new ArrayList<>();

    private List<ProductDTO> productPromotions = new ArrayList<>();

    @NotEmpty(message = "{field.name.mandatory}")
    private String name;

    @PositiveOrZero(message = "{field.price.invalid}")
    private Double price;

    @NotEmpty(message = "{field.description.mandatory}")
    private String description;

    @NotEmpty(message = "{field.category.mandatory}")
    private List<CategoryDTO> categories = new ArrayList<>();


	public ProductDTO(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.price = product.getPrice();
		this.description = product.getDescription();
	}
 

}
