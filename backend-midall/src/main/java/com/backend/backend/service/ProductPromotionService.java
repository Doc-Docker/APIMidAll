package com.backend.backend.service;

import com.backend.backend.domain.Category;
import com.backend.backend.domain.CategoryPromotion;
import com.backend.backend.domain.Product;
import com.backend.backend.domain.ProductPromotion;
import com.backend.backend.dto.CategoryPromotionDTO;
import com.backend.backend.dto.ProductPromotionDTO;
import com.backend.backend.exceptions.BadRequestException;
import com.backend.backend.repository.CategoryPromotionRepository;
import com.backend.backend.repository.CategoryRepository;
import com.backend.backend.repository.ProductPromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductPromotionService {

    @Autowired
    ProductPromotionRepository productPromotionRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryRepository categoryRepository;


    public ProductPromotion insert(ProductPromotionDTO objDto){
        this.validateDTOProducts(objDto);
        objDto.setId(null);

        return productPromotionRepository.save(this.fromDTO(objDto));
        //return catPromoRep.saveAndFlush(this.fromDTO(objDto));
    }

    public ProductPromotion find(Integer id){
        Optional<ProductPromotion> productPromotion = productPromotionRepository.findById(id);
        return productPromotion.orElseThrow();
    }

    private void validateDTOProducts(ProductPromotionDTO productPromotionDTO) {
        productPromotionDTO.getProducts().forEach(givenProduct -> {
            Product product = productService.findById(givenProduct.getId());
            if(givenProduct.getName() != null && !Objects.equals(givenProduct.getName(), product.getName()))
                throw new BadRequestException("Product name doesn't match with the given id");
        });
    }

    private ProductPromotion fromDTO(ProductPromotionDTO productPromotionDTO) {
        ProductPromotion productPromotion = new ProductPromotion(productPromotionDTO.getId(),productPromotionDTO.getDiscount());

        productPromotion.getProducts().addAll(productPromotionDTO.getProducts().stream().map(
                productDTO -> new Product(productDTO.getId(), productDTO.getName(), productDTO.getPrice(), productDTO.getDescription())
        ).collect(Collectors.toList()));

        return productPromotion;
    }

}
