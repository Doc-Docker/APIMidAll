package com.backend.backend.repository;

import com.backend.backend.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  CategoryRepository extends JpaRepository<Category, Integer> {

}
