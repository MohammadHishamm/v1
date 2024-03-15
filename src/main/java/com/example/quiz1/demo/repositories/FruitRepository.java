package com.example.quiz1.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quiz1.demo.models.User;
import com.example.quiz1.demo.models.fruit;


public interface FruitRepository extends JpaRepository<fruit,Integer>{
    fruit findById(int fruitId);
}
