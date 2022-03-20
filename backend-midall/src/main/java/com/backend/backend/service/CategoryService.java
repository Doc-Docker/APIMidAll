package com.backend.backend.service;

import com.backend.backend.domain.Category;
import com.backend.backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository rep;

    public Category find(Integer id){
        Optional<Category> cat = rep.findById(id);
        return cat.orElseThrow();
    }

    public Category insert(Category obj){
        obj.setId(null);
        return rep.save(obj);
    }

    public Category update(Category obj){
        find(obj.getId());
        return rep.save(obj);
    }

    public void delete(Integer id){
        find(id);
        rep.deleteById(id);
    }

    public List<Category> findAll(){
        return rep.findAll();
    }
}