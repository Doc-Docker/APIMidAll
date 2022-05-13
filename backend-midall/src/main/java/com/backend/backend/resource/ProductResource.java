package com.backend.backend.resource;

import com.backend.backend.domain.Product;
import com.backend.backend.domain.ProductPromotion;
import com.backend.backend.domain.SearchProductFilters;
import com.backend.backend.dto.ProductDTO;
import com.backend.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductResource {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProductDTO> findById(@PathVariable Integer id) {
        Product foundProduct = productService.findById(id);

        return ResponseEntity.ok().body(productService.fromDomain(foundProduct));
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<List<ProductDTO>> findAll(SearchProductFilters filters) {
        List<Product> productList = productService.findAll(filters);
        List<ProductDTO> response = productList.stream().map(
                product -> productService.fromDomain(product)
        ).collect(Collectors.toList());

        return ResponseEntity.ok().body(response);
    }

    @GetMapping ("/products/preco")
    public ResponseEntity<Double> preco(@RequestBody List<Product> lista, SearchProductFilters filters) {

        Double precoTotal = 0.0;

        for(Product produto : lista){
            precoTotal = precoTotal + produto.getPrice();

            if(!produto.getProductPromotions().isEmpty()){
                List<ProductPromotion> promocoes = produto.getProductPromotions();
                for(ProductPromotion promocao : promocoes){
                    precoTotal = precoTotal - promocao.getDiscount();
                }
            }

        }


        return ResponseEntity.ok().body(precoTotal);
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<?> insert(@RequestBody @Valid ProductDTO newProduct) {
        Product insertedProduct = productService.insert(newProduct);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}").buildAndExpand(insertedProduct.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> update(@RequestBody ProductDTO updatedProduct, @PathVariable Integer id) {
        productService.update(id, updatedProduct);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        productService.delete(id);

        return ResponseEntity.noContent().build();
    }

}