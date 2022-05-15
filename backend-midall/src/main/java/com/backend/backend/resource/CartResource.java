package com.backend.backend.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.backend.backend.domain.Cart;
import com.backend.backend.domain.Category;
import com.backend.backend.dto.CartDTO;
import com.backend.backend.dto.CategoryDTO;
import com.backend.backend.service.CartService;
import com.backend.backend.service.CategoryService;
import com.backend.backend.service.ProductService;

@RestController
@RequestMapping(value = "/cart")
public class CartResource {
	
    @Autowired
    private CartService cartService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Cart cat = cartService.find(id);
        return ResponseEntity.ok().body(cat);
    }

   
    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Cart obj) {
        obj = cartService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
    	cartService.delete(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping
    public ResponseEntity<List<CartDTO>> findAll() {
        List<Cart> list = cartService.findAll();

        List<CartDTO> listDto = list.stream().map(cartService -> mapper.map(cartService, CartDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);


    }
	

}
