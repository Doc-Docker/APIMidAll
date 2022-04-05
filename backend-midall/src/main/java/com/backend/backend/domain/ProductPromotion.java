package com.backend.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of={"id"})
public class ProductPromotion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double discount;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "PROMOTION_PRODUCT",
            joinColumns = @JoinColumn(name = "categoryPromotion_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();



    public ProductPromotion(Integer id, Double discount) {
        this.id = id;
        this.discount = discount;
    }
}
