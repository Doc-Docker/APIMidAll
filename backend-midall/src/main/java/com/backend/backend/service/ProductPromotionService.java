package com.backend.backend.service;

import com.backend.backend.domain.Category;
import com.backend.backend.domain.Product;
import com.backend.backend.domain.ProductPromotion;
import com.backend.backend.dto.CategoryDTO;
import com.backend.backend.dto.ProductDTO;
import com.backend.backend.dto.ProductPromotionDTO;
import com.backend.backend.enumerate.TypePromotion;
import com.backend.backend.exceptions.BadRequestException;
import com.backend.backend.repository.ProductPromotionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductPromotionService {

	@Autowired
	ProductPromotionRepository productPromotionRepository;

	@Autowired
	ProductService productService;

	public ProductPromotion findById(Integer id) {
		Optional<ProductPromotion> promotion = productPromotionRepository.findById(id);

		return promotion.get();
	}

	public ProductPromotion insert(ProductPromotionDTO objDto) {
		this.validateDTOProducts(objDto);
		objDto.setId(null);

		return productPromotionRepository.save(this.fromDTO(objDto));

	}

	public ProductPromotion find(Integer id) {
		Optional<ProductPromotion> productPromotion = productPromotionRepository.findById(id);
		return productPromotion.orElseThrow();
	}

	private void validateDTOProducts(ProductPromotionDTO productPromotionDTO) {
		productPromotionDTO.getProducts().forEach(givenProduct -> {
			Product product = productService.findById(givenProduct.getId());
			if (givenProduct.getName() != null && !Objects.equals(givenProduct.getName(), product.getName()))
				throw new BadRequestException("Product name doesn't match with the given id");
		});
	}

	private ProductPromotion fromDTO(ProductPromotionDTO productPromotionDTO) {
		ProductPromotion productPromotion = new ProductPromotion(productPromotionDTO.getId(),
				productPromotionDTO.getName(), productPromotionDTO.getStartDate(), productPromotionDTO.getFinalDate(),
				productPromotionDTO.getIsActive(), productPromotionDTO.getTypePromotion(),
				productPromotionDTO.getReceivePromotion(), productPromotionDTO.getDiscount());

		productPromotion.getProducts()
				.addAll(productPromotionDTO.getProducts().stream().map(productDTO -> new Product(productDTO.getId(),
						productDTO.getName(), productDTO.getPrice(), productDTO.getDescription()))
						.collect(Collectors.toList()));

		return productPromotion;
	}

	public ProductPromotionDTO fromDomain(ProductPromotion promotion) {
		ProductPromotionDTO promotionDTO = new ProductPromotionDTO(promotion);
		promotionDTO.getProducts().addAll(
				promotion.getProducts().stream().map(product -> new ProductDTO(product)).collect(Collectors.toList()));
		return promotionDTO;

	}

//	public Double applyPromotion(List<Product> products) {
//		Double type = 0.0;
//		for (Product product : products) {
//
//			if (product.getProductPromotions().get(0).getIsActive() == true) {
//
//				type = promotionService.retornaProdutoPromocao(product);
//
////				if (product.getProductPromotions().get(0).getTypePromotion().getCode() == 1
////						|| product.getProductPromotions().get(0).getTypePromotion().getCode() == 2) {
////
////					if (product.getPrice() > product.getProductPromotions().get(0).getDiscount()) {
////						type = product.getPrice() - product.getProductPromotions().get(0).getDiscount();
//
//				return type;
//			}
//		}
////			}
////
////		}
//
//		return type;
//
//	}

	public Double retornaProdutoPromocao(List<Product> product) {
         
		List<ProductPromotion> productPromotion  = new ArrayList<ProductPromotion>(); 
		
	    productPromotion.addAll(product.get(0).getProductPromotions());
		int n = productPromotion.size();
		int i = 0;
		Double desconto = 0.0;
		for (i = 0; i < n; i++) {

			
			while(i<n) {
				Double valor = 0.0;
				Double valor2 = 0.0;
				if (product.get(0).getProductPromotions().get(i).getIsActive() == true) {
					if (product.get(0).getProductPromotions().get(i).getReceivePromition().getCode() == productPromotion.get(i).getReceivePromition().getCode()) {
						if (product.get(0).getProductPromotions().get(i).getTypePromotion().getCode() == 1) {
							desconto = product.get(0).getPrice() - product.get(0).getProductPromotions().get(i).getDiscount();
							valor2 = desconto;
							if ( valor < valor2) {
								desconto = valor2;

							}
						}

						if (product.get(0).getProductPromotions().get(i).getTypePromotion().getCode() == 2) {
							desconto = product.get(0).getPrice() - ((product.get(0).getProductPromotions().get(i).getDiscount() / 100)
									* product.get(0).getPrice());
							valor2 = desconto;
							if (valor < valor2) {
								desconto = valor2;

							}
						}

					}
				}
				valor = desconto;
				i++;
			}
			
			
		}
		
		return desconto;
	}
	
	public List<ProductPromotion> findAll() {
		List<ProductPromotion> promotionList = productPromotionRepository.findAll();
		if (promotionList.isEmpty()) {
			throw new BadRequestException("");
		}
		return productPromotionRepository.findAll();
	}

}
