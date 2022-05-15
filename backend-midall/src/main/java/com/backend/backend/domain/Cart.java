package com.backend.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@EqualsAndHashCode(of={"id"})
@NoArgsConstructor
public class Cart implements Serializable {
	 private static final long serialVersionUID = 1L;

	    @Id
	    @GeneratedValue(strategy= GenerationType.IDENTITY)
	    private Integer id;

	    private String name;

	    private Double price;

	    private String description;

	

	    public Cart(Integer id, String name, Double price, String description) {
	        this.id = id;
	        this.name = name;
	        this.price = price;
	        this.description = description;
	    }
}
