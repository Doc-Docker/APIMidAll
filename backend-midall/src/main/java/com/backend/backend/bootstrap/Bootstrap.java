package com.backend.backend.bootstrap;

import com.backend.backend.domain.Category;
import com.backend.backend.domain.Product;
import com.backend.backend.repository.CategoryRepository;
import com.backend.backend.repository.ProductRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@NoArgsConstructor
public class Bootstrap implements CommandLineRunner {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        Category cat1 = new Category(null, "informatica");
        Category cat2 = new Category(null,"Escritorio");

        Product p1 = new Product(null, "Computador", 2000.00, "Description p1");
        Product p2 = new Product(null, "Impressora", 800.00, "Description p2");
        Product p3 = new Product(null, "Mouse", 80.00, "Description p3");

        cat1.getProducts().addAll(Arrays.asList(p1,p2,p3));
        cat2.getProducts().addAll(Arrays.asList(p2));

        p1.getCategories().addAll(Arrays.asList(cat1));
        p2.getCategories().addAll(Arrays.asList(cat1,cat2));
        p3.getCategories().addAll(Arrays.asList(cat1));

        categoryRepository.saveAll(Arrays.asList(cat1,cat2));

        productRepository.saveAll(Arrays.asList(p1,p2,p3));

        System.out.println("CONECTADO");
        System.out.println("CONECTADO");
        System.out.println("CONECTADO");
        System.out.println("CONECTADO");
        System.out.println("CONECTADO");

    }
}