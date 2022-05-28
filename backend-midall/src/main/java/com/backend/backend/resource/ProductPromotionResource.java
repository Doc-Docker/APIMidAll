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
import com.backend.backend.service.ProductPromotionService;
import com.backend.backend.service.ProductService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
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
	public ResponseEntity<?> insert(@RequestBody ProductPromotionDTO obj) {
		ProductPromotion insertedPromotion = productPromotionService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		ProductPromotion productPromotion = productPromotionService.find(id);

		ProductPromotionService promotion = new ProductPromotionService();

		Double teste = promotion.retornaProdutoPromocao(productPromotion.getProducts());

		System.out.println(teste);
		return ResponseEntity.ok().body(productPromotion);
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Integer id, @RequestBody ProductPromotion promotion) {

		productPromotionRepository.findById(id).map(ProductPromotion -> {
			promotion.setIsActive(promotion.getIsActive());
			return productPromotionRepository.save(promotion);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));

	}

	@PutMapping("/insert/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void insertProduct(@PathVariable Integer id, @RequestBody ProductPromotion promotion) {
		SearchProductFilters filters = new SearchProductFilters();
		productPromotionRepository.findById(id).map(ProductPromotion -> {
			promotion.setProducts(products.findAll(filters));
			return productPromotionRepository.save(promotion);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));

	}

	@PutMapping("/remove/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeProduct(@PathVariable Integer id, @RequestBody ProductPromotion promotion) {
		SearchProductFilters filters = new SearchProductFilters();
		productPromotionRepository.findById(id).map(ProductPromotion -> {
			products.delete(id);
			return productPromotionRepository.save(promotion);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));

	}

	@GetMapping("/promotions")
	public ResponseEntity<List<ProductPromotionDTO>> findAll() {
		List<ProductPromotion> listProductPromotion = productPromotionService.findAll();
		List<ProductPromotionDTO> listProductPromotionDto = listProductPromotion.stream()
				.map(productPromotion -> productPromotionService.fromDomain(productPromotion))
				.collect(Collectors.toList());
				

		return ResponseEntity.ok().body(listProductPromotionDto);

	} 


}