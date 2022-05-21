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

	ProductPromotionService promotionService;

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
				productPromotionDTO.getStartDate(), productPromotionDTO.getFinalDate(),
				productPromotionDTO.getIsActive(), productPromotionDTO.getTypePromotion(),
				productPromotionDTO.getDiscount());

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

	public Double applyPromotion(List<Product> products, Double value) {


		for (Product product : products) {
			if (product.getProductPromotions().get(0).getIsActive() == true) {
				if (product.getProductPromotions().get(0).getTypePromotion().getCode() == 1) {
					if (product.getPrice() > value) {
						Double type = product.getPrice() - value;

						return type;
					}
				}
			}
		}

		return 1.0;

	}

	public List<ProductPromotion> findAll() {
		List<ProductPromotion> promotionList = productPromotionRepository.findAll();
		if (promotionList.isEmpty()) {
			throw new BadRequestException("");
		}
		return productPromotionRepository.findAll();
	}

}
