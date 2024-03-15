package com.example.quiz1.demo.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quiz1.demo.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
}
