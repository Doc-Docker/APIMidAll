package com.backend.backend.resource;

import com.backend.backend.domain.Category;
import com.backend.backend.dto.CategoryDTO;
import com.backend.backend.service.CategoryService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Category cat = categoryService.find(id);
        return ResponseEntity.ok().body(cat);
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Category obj) {
        obj = categoryService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Category obj, @PathVariable Integer id) {

        obj.setId(id);
        obj = categoryService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<Category> list = categoryService.findAll();

        List<CategoryDTO> listDto = list.stream().map(categoryService -> mapper.map(categoryService, CategoryDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);


    }

}