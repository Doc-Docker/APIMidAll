package com.backend.backend.resource;

import com.backend.backend.domain.Category;
import com.backend.backend.dto.categoryPromotionDTO;
import com.backend.backend.service.categoryPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/category-promotions")
public class categoryPromotionResource {

    @Autowired
    categoryPromotionService catPromoService;



    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> insert(@RequestBody categoryPromotionDTO obj){
        obj = catPromoService.insert(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
