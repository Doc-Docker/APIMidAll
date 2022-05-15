package com.backend.backend.service;

import com.backend.backend.domain.Category;
import com.backend.backend.domain.Product;
import com.backend.backend.domain.SearchProductFilters;
import com.backend.backend.dto.CategoryDTO;
import com.backend.backend.dto.ProductDTO;
import com.backend.backend.exceptions.BadRequestException;
import com.backend.backend.exceptions.ObjectNotFoundException;
import com.backend.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public Product findById(Integer id) {
        Optional<Product> product = productRepository.findById(id);

        return product.get();
    }

    public List<Product> findAll(SearchProductFilters filters) {
        List<Product> productList = productRepository.findAll();

        String productNameFilter = filters.getProductName();
        if(productNameFilter != null) {
            productList = productList.stream().filter(product ->
                    product.getName().equals(productNameFilter)
            ).collect(Collectors.toList());
        }

        String categoryNameFilter = filters.getCategoryName();
        if(categoryNameFilter != null) {
            productList = productList.stream().filter(
                    product -> product.getCategories().stream().anyMatch(
                            category -> category.getName().equals(categoryNameFilter)
                    )
            ).collect(Collectors.toList());
        }

        Integer categoryIdFilter = filters.getCategoryId();
        if(categoryIdFilter != null) {
            productList = productList.stream().filter(
                    product -> product.getCategories().stream().anyMatch(
                            category -> category.getId().equals(categoryIdFilter)
                    )
            ).collect(Collectors.toList());
        }
        
        if(productList.isEmpty()) { //Nenhum produto cadastrado
        	throw new BadRequestException("No products registered"); 
        }	

        return productList;
    }

    public Product insert(ProductDTO productDTO) {
        //this.validateDTOCategories(productDTO);
        productDTO.setId(null);

        return productRepository.save(this.fromDTO(productDTO));
    }

    public Product update(Integer id, ProductDTO productDTO) {
        Product product = this.findById(id);
        this.validateDTOCategories(productDTO);
        this.validateDTOProducts(id,productDTO); //Validação do produto a ser inserido

        Product productReceived = this.fromDTO(productDTO);

        Product finalProduct = new Product(
                id,
                productReceived.getName() != null ? productReceived.getName() : product.getName(),
                productReceived.getPrice() != null ? productReceived.getPrice() : product.getPrice(),
                productReceived.getDescription() != null? productReceived.getDescription() : product.getDescription()
        );
        finalProduct.setCategories(
                productReceived.getCategories() != null ? productReceived.getCategories() : product.getCategories()
        );

        return productRepository.save(finalProduct);
    }

    public void delete(Integer id) {
        this.findById(id);

        productRepository.deleteById(id);
    }
    
    private void validateDTOProducts(Integer id,ProductDTO productDTO) {
    	
    	if(productDTO.getName().isEmpty()) { //Produto com nome vazio
        	throw new BadRequestException("Product with empty name"); 
        }

    	
        List<Product> productList = productRepository.findAll();
        int n = productList.size();
        int i;
        
        for (i=0; i<n; i++) { //Produto com nome duplicado
            if(productList.get(i).getName().equals(productDTO.getName()) && id == null) {
            	throw new BadRequestException("Product with duplicate name"); 
            }
          }
        
        
    }

    private void validateDTOCategories(ProductDTO productDTO) {
        productDTO.getCategories().forEach(givenCategory -> {
            Category category = categoryService.find(givenCategory.getId());
            if(givenCategory.getName() != null && !Objects.equals(givenCategory.getName(), category.getName()))
                throw new BadRequestException("Category name doesn't match with the given id");
        });
    }

    private Product fromDTO(ProductDTO productDTO) {
        Product product = new Product(productDTO.getId() , productDTO.getName(), productDTO.getPrice(), productDTO.getDescription());
        product.getCategories().addAll(productDTO.getCategories().stream().map(
                categoryDTO -> new Category(categoryDTO.getId(), categoryDTO.getName())
        ).collect(Collectors.toList()));

        return product;
    }

    public ProductDTO fromDomain(Product product) {
        ProductDTO productDTO = new ProductDTO(product);
        productDTO.getCategories().addAll(product.getCategories().stream().map(
                category -> new CategoryDTO(category)
        ).collect(Collectors.toList()));

        return productDTO;
    }
}
