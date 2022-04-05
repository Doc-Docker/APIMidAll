package com.backend.backend.dto;

import com.backend.backend.domain.Product;
import com.backend.backend.domain.ProductPromotion;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of={"id"})
public class ProductDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotBlank(message = "Name is required")
    private String name;

    @PositiveOrZero(message = "Price must be a value higher or equal to 0")
    private Double price;

    private String description;

    private List<CategoryDTO> categories = new ArrayList<>();

    private List<ProductPromotionDTO> productPromotions = new ArrayList<>();

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
    }

}
