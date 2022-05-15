package com.backend.backend.repository;

import com.backend.backend.domain.Cart;
import com.backend.backend.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	

}
