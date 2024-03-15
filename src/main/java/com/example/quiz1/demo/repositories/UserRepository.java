package com.example.quiz1.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quiz1.demo.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findById(Long id);
}