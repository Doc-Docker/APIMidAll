package com.backend.backend.resource;

import com.backend.backend.domain.ProductPromotion;
import com.backend.backend.dto.ProductPromotionDTO;
import com.backend.backend.service.ProductPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/product-promotions")
@CrossOrigin("http://localhost:4200")
public class ProductPromotionResource {

    @Autowired
    ProductPromotionService productPromotionService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> insert(@RequestBody ProductPromotionDTO obj){
        ProductPromotion insertedPromotion = productPromotionService.insert(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id){
        ProductPromotion productPromotion = productPromotionService.find(id);
        return ResponseEntity.ok().body(productPromotion);
    }
}
