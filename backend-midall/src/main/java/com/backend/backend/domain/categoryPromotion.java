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
public class categoryPromotion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double discount;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "PROMOTION_CATEGORY",
            joinColumns = @JoinColumn(name = "categoryPromotion_id"),
            inverseJoinColumns = @JoinColumn(name = "Category_id")
    )
    private List<Category> categories = new ArrayList<>();

    public categoryPromotion(Integer id, Double discount) {
        this.id = id;
        this.discount = discount;
    }
}
