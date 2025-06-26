package com.example.advanced_java_project.Repositories;

import com.example.advanced_java_project.Models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> getByUserId(Long userId);
}
