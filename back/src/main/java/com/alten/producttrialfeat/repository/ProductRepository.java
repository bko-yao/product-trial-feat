package com.alten.producttrialfeat.repository;

import com.alten.producttrialfeat.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository de Product.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
