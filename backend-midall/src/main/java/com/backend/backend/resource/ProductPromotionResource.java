package com.backend.backend.resource;

import com.backend.backend.domain.Category;
import com.backend.backend.domain.Product;
import com.backend.backend.domain.ProductPromotion;
import com.backend.backend.domain.SearchProductFilters;
import com.backend.backend.dto.CategoryDTO;
import com.backend.backend.dto.ProductDTO;
import com.backend.backend.dto.ProductPromotionDTO;
import com.backend.backend.repository.ProductPromotionRepository;
import com.backend.backend.repository.ProductRepository;
import com.backend.backend.service.Json;
import com.backend.backend.service.ProductPromotionService;
import com.backend.backend.service.ProductService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.Serializable;
import java.net.URI;
import java.security.DomainLoadStoreParameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product-promotions")
public class ProductPromotionResource {

	@Autowired
	ProductPromotionService productPromotionService;

	@Autowired
	ProductPromotionRepository productPromotionRepository;

	@Autowired
	ProductService products;

	@PostMapping
	public ResponseEntity<?> createPromotion(@RequestBody @Valid ProductPromotionDTO obj) {
		ProductPromotion insertedPromotion = productPromotionService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductPromotionDTO> findById(@PathVariable Integer id) {
		ProductPromotion foundProduct = productPromotionService.findById(id);

		return ResponseEntity.ok().body(productPromotionService.fromDomain(foundProduct));
	}

	@PutMapping("disable/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void disablePromotion(@PathVariable Integer id) {

		Optional<ProductPromotion> productPromotion = productPromotionRepository.findById(id);
		ProductPromotion promotion = productPromotion.get();

		productPromotionRepository.findById(id).map(ProductPromotion -> {
			if (promotion.getIsActive() == false) {

				promotion.setIsActive(true);
				return productPromotionRepository.save(promotion);
			}

			if (promotion.getIsActive() == true) {

				promotion.setIsActive(false);
				return productPromotionRepository.save(promotion);
			}

			return productPromotionRepository.save(promotion);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));

	}

	@GetMapping("/list")
	public ResponseEntity<List<ProductPromotionDTO>> findAll() {
		List<ProductPromotion> listProductPromotion = productPromotionService.findAll();
		List<ProductPromotionDTO> listProductPromotionDto = listProductPromotion.stream()
				.map(productPromotion -> productPromotionService.fromDomain(productPromotion))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(listProductPromotionDto);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		productPromotionService.delete(id);

		return ResponseEntity.noContent().build();
	}


	@PostMapping("/discount")
	public ResponseEntity<?> methos(@RequestBody Json json) {
		Integer id = json.getId();
		Integer quantidade = json.getQuantidade();
		Integer total = json.getTotal();
		ResponseEntity<?> discount = productPromotionService.retornaProdutoPromocao(id, quantidade, total);
		return discount;
	}

}