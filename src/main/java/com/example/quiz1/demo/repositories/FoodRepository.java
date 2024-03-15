package com.example.quiz1.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quiz1.demo.models.Food;

public interface FoodRepository extends JpaRepository<Food, Integer> {
    List<Food> findAllByCategoryId(int id);
}
