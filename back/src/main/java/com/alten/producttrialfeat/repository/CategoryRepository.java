package com.alten.producttrialfeat.repository;

import com.alten.producttrialfeat.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository de Category
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCode(String code);
}
