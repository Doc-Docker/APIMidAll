package com.backend.backend.bootstrap;

import com.backend.backend.domain.Category;
import com.backend.backend.domain.Product;
import com.backend.backend.domain.ProductPromotion;
import com.backend.backend.repository.CategoryRepository;
import com.backend.backend.repository.ProductPromotionRepository;
import com.backend.backend.repository.ProductRepository;
import com.backend.backend.service.ProductPromotionService;
import com.backend.backend.service.ProductService;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class Bootstrap implements CommandLineRunner {
	@Autowired
	ProductPromotionRepository productPromotion;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ProductRepository productRepository;

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "informatica");
		Category cat2 = new Category(null, "Escritorio");
	


		Product p1 = new Product(null,0, "Computador", 2000.00, "Description p1");
		Product p2 = new Product(null, 0, "Impressora", 800.00, "Description p2");
		Product p3 = new Product(null, 0, "Mouse", 80.00, "Description p3");
		Product p4 = new Product(null, 0, "Mouse sem fio", 100.00, "Description p4");
		Product p5 = new Product(null, 0, "Criado Mudo", 200.00, "Description p5");
		Product p6 = new Product(null, 0, "Mesa", 300.00, "Description p6");

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3, p4));
		cat2.getProducts().addAll(Arrays.asList(p2, p5, p6));
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		p4.getCategories().addAll(Arrays.asList(cat1));
		p5.getCategories().addAll(Arrays.asList(cat2));
		p6.getCategories().addAll(Arrays.asList(cat2));

		

		categoryRepository.saveAll(Arrays.asList(cat1, cat2));

		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6));

	

		System.out.println("CONECTADO");
		System.out.println("CONECTADO");
		System.out.println("CONECTADO");
		System.out.println("CONECTADO");
		System.out.println("CONECTADO");

	}
}