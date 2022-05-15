package com.backend.backend.resource;

import com.backend.backend.domain.CategoryPromotion;
import com.backend.backend.dto.CategoryPromotionDTO;
import com.backend.backend.service.CategoryPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/category-promotions")
public class CategoryPromotionResource {

    @Autowired
    CategoryPromotionService categoryPromotionService;

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody CategoryPromotionDTO obj){
        CategoryPromotion insertedPromotion = categoryPromotionService.insert(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping ("/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id){
        CategoryPromotion cat = categoryPromotionService.find(id);
        return ResponseEntity.ok().body(cat);
    }
}
