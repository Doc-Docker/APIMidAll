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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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
		objDto.setIsActive(true);

		return productPromotionRepository.save(this.fromDTO(objDto));

	}

	public void delete(Integer id) {
		this.findById(id);

		productPromotionRepository.deleteById(id);
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
				productPromotionDTO.getName(), productPromotionDTO.getIsActive(), productPromotionDTO.getIdCategory(),
				productPromotionDTO.getTypePromotion(), productPromotionDTO.getReceivePromotion(),
				productPromotionDTO.getQuantidade(), productPromotionDTO.getTotalCompra(),
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

	public ResponseEntity<?> retornaProdutoPromocao(@RequestBody Integer id, Integer quantidade, Integer total) {

		List<ProductPromotion> promotios = productPromotionRepository.findAll();

		List<ProductPromotion> productPromotion = new ArrayList<ProductPromotion>();

		Product product = productService.findById(id);

		productPromotion.addAll(product.getProductPromotions());
		int n = productPromotion.size();

		int i = 0;
		Double desconto = 0.0;
		Double valor = 0.0;
		Double valor2 = 0.0;
		
		
		if (total > 0) {
			System.out.println("Total");
			for (ProductPromotion promocao : promotios) {
				System.out.println("Total");
				if (promocao.getReceivePromotion().getCode() == 2 && total > promocao.getTotalCompra()) {
					if (promocao.getTypePromotion().getCode() == 1) {
						valor2 = promocao.getDiscount();

						if (valor2 > valor) {
							desconto = valor2;

						}
					}

					if (promocao.getTypePromotion().getCode() == 2) {
						valor2 = ((promocao.getDiscount() / 100) *  total );

						if (valor2 > valor) {
							desconto = valor2;

						}
						System.out.println(desconto);
					}
				}
			}
		}
		for (i = 0; i < n; i++) {

			while (i < n) {

				if (product.getProductPromotions().get(i).getIsActive() == true) {

					// Promotion Product
					if (product.getProductPromotions().get(i).getReceivePromotion().getCode() == 1) {

						if (product.getProductPromotions().get(i).getTypePromotion().getCode() == 1) {
							valor2 = quantidade * product.getProductPromotions().get(i).getDiscount();
							;
							if (valor2 > valor) {
								desconto = valor2;

							}
						}

						if (product.getProductPromotions().get(i).getTypePromotion().getCode() == 2) {
							valor2 = ((product.getProductPromotions().get(i).getDiscount() / 100)
									* (product.getPrice() * quantidade));

							if (valor2 > valor) {
								desconto = valor2;

							}
						}
					}


					// Promotion quantidade
					if (product.getProductPromotions().get(i).getReceivePromotion().getCode() == 3
							&& quantidade > product.getProductPromotions().get(i).getQuantidade()) {
						if (product.getProductPromotions().get(i).getTypePromotion().getCode() == 1) {
							valor2 = quantidade * product.getProductPromotions().get(i).getDiscount();

							if (valor2 > valor) {
								desconto = valor2;

							}
						}

						if (product.getProductPromotions().get(i).getTypePromotion().getCode() == 2) {
							valor2 = ((product.getProductPromotions().get(i).getDiscount() / 100)
									* (product.getPrice() * quantidade));

							if (valor2 > valor) {
								desconto = valor2;

							}
						}
					}

					// Promotion Category
					if (product.getProductPromotions().get(i).getReceivePromotion().getCode() == 4 && product
							.getProductPromotions().get(i).getIdCategory() == product.getCategories().get(0).getId()) {
						if (product.getProductPromotions().get(i).getTypePromotion().getCode() == 1) {
							valor2 = quantidade * product.getProductPromotions().get(i).getDiscount();

							if (valor2 > valor) {
								desconto = valor2;

							}
						}

						if (product.getProductPromotions().get(i).getTypePromotion().getCode() == 2) {
							valor2 = ((product.getProductPromotions().get(i).getDiscount() / 100)
									* (product.getPrice() * quantidade));

							if (valor2 > valor) {
								desconto = valor2;

							}
						}
					}

				}
				valor = desconto;
				i++;
			}

		}

		return new ResponseEntity<>(desconto, HttpStatus.OK);
	}

	public List<ProductPromotion> findAll() {
		List<ProductPromotion> promotionList = productPromotionRepository.findAll();
		if (promotionList.isEmpty()) {
			throw new BadRequestException("");
		}
		return productPromotionRepository.findAll();
	}

}
