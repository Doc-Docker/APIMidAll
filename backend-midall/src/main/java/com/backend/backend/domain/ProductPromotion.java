package com.backend.backend.domain;

import com.backend.backend.dto.ProductDTO;
import com.backend.backend.enumerate.ReceivePromotion;
import com.backend.backend.enumerate.TypePromotion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = { "id" })
public class ProductPromotion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;

	private Boolean isActive;
	
	private Integer idCategory;

	private Integer typePromotion;
	
	private Integer receivePromotion;

	private Integer quantidade;
	
	private Integer totalCompra;
	
	private Double discount;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "PROMOTION_PRODUCT", joinColumns = @JoinColumn(name = "productPromotion_id"), inverseJoinColumns = @JoinColumn(name = "Product_id"))
	private List<Product> products = new ArrayList<>();
	

	public ProductPromotion(Integer id,String name, Boolean isActive, Integer idCategory,
			TypePromotion typePromotion, ReceivePromotion receivePromotion, Integer quantidade, Integer totalCompra, Double discount) {
		this.id = id;
		this.name= name;
		this.isActive = isActive;
		this.idCategory = idCategory;
		setTypePromotion(typePromotion);
		setReceivePromotion(receivePromotion);
		this.quantidade = quantidade;
		this.totalCompra = totalCompra;
		this.discount = discount;

	}

	public void setReceivePromotion(ReceivePromotion receivePromotion) {
		if (receivePromotion != null) {
			this.receivePromotion = receivePromotion.getCode();
		}

	}

	public ReceivePromotion getReceivePromotion() {
		return ReceivePromotion.valueOf(receivePromotion);
	}
	
	

	public void setTypePromotion(TypePromotion typePromotion) {
		if (typePromotion != null) {
			this.typePromotion = typePromotion.getCode();
		}

	}

	public TypePromotion getTypePromotion() {
		return TypePromotion.valueOf(typePromotion);
	}
	
}
