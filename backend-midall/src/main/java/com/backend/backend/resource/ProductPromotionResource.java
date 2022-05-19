package com.backend.backend.resource;

import com.backend.backend.domain.Category;
import com.backend.backend.domain.ProductPromotion;
import com.backend.backend.dto.CategoryDTO;
import com.backend.backend.dto.ProductPromotionDTO;
import com.backend.backend.service.ProductPromotionService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product-promotions")
public class ProductPromotionResource {

    @Autowired
    ProductPromotionService productPromotionService;
    
    @Autowired
    private ModelMapper mapper;

    @PostMapping                        
    public ResponseEntity<?> insert(@RequestBody ProductPromotionDTO obj){
        ProductPromotion insertedPromotion = productPromotionService.insert(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping ("/{id}")                               
    public ResponseEntity<?> find(@PathVariable Integer id){
        ProductPromotion productPromotion = productPromotionService.find(id);
        return ResponseEntity.ok().body(productPromotion);
    }
    @GetMapping
    public ResponseEntity<List<ProductPromotionDTO>> findAll() {
        List<ProductPromotion> listProductPromotion = productPromotionService.findAll();

        List<ProductPromotionDTO> listProductPromotionDto = listProductPromotion.stream().map(productPromotionService -> mapper.map(productPromotionService, ProductPromotionDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listProductPromotionDto);


    }
}