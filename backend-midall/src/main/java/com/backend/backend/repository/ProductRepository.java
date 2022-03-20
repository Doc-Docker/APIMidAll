package com.backend.backend.repository;

import com.backend.backend.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  ProductRepository extends JpaRepository<Product, Integer> {
}
