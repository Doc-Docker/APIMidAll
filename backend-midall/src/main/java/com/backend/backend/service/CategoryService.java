package com.backend.backend.service;

import com.backend.backend.domain.Category;
import com.backend.backend.dto.CategoryDTO;
import com.backend.backend.exceptions.BadRequestException;
import com.backend.backend.exceptions.ObjectNotFoundException;
import com.backend.backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository rep;

    public Category find(Integer id) {
        Optional<Category> cat = rep.findById(id);
        return cat.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found!: Id: " + id + ", Type: " + Category.class.getName()
        ));
    }

    public Category insert(Category obj){
        obj.setId(null);

        if(obj.getName().isEmpty()){
            throw new BadRequestException("Category with empty name");
        }

        for(Category cat : rep.findAll()){
            if(cat.getName().equals(obj.getName()) ){
                throw new BadRequestException("Category does exist");
            }
        }
        return rep.save(obj);
    }

    public Category update(Category obj){
        find(obj.getId());
        if(obj.getName().equals(findAll())){
            throw new BadRequestException("Name similar to the one already registered");
        }
        return rep.save(obj);
    }

    public void delete(Integer id){
        find(id);
        if(find(id) == null){
            throw new BadRequestException("No ID entered");
        }
        rep.deleteById(id);
    }

    public List<Category> findAll() {
        List<Category> categoryList = rep.findAll();
        if (categoryList.isEmpty()) { //Nenhuma categoria cadastrada
            throw new BadRequestException("No category registered");
        }
        return rep.findAll();
    }
}