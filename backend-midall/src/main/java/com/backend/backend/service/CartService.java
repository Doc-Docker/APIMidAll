package com.backend.backend.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.backend.domain.Cart;
import com.backend.backend.domain.Category;
import com.backend.backend.domain.Product;
import com.backend.backend.domain.SearchProductFilters;
import com.backend.backend.dto.CategoryDTO;
import com.backend.backend.dto.ProductDTO;
import com.backend.backend.exceptions.BadRequestException;
import com.backend.backend.exceptions.ObjectNotFoundException;
import com.backend.backend.repository.CartRepository;
import com.backend.backend.repository.CategoryRepository;
import com.backend.backend.resource.CartResource;

@Service
public class CartService {

    @Autowired
    private CartRepository rep;

    public Cart find(Integer id) {
        Optional<Cart> cat = rep.findById(id);
        return cat.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found!: Id: " + id + ", Type: " + Cart.class.getName()
        ));
    }

    public Cart insert(Cart obj){
        obj.setId(null);

        if(obj.getName().isEmpty()){
            throw new BadRequestException("Category with empty name");
        }

        for(Cart cat : rep.findAll()){
            if(cat.getName().equals(obj.getName()) ){
                throw new BadRequestException("Category does exist");
            }
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

    public List<Cart> findAll() {
        List<Cart> categoryList = rep.findAll();
        if (categoryList.isEmpty()) { 
            throw new BadRequestException("No category registered");
        }
        return rep.findAll();
    }

}
