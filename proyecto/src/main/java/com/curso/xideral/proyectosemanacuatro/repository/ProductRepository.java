package com.curso.xideral.proyectosemanacuatro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.curso.xideral.proyectosemanacuatro.models.Category;
import com.curso.xideral.proyectosemanacuatro.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
      List<Product> findByCategory(Category category);
    List<Product> findByPriceBetween(Double min, Double max);
    List<Product> findByNameContainingIgnoreCase(String keyword);
    boolean existsByNameIgnoreCase(String name);

    @Query("SELECT p FROM Product p WHERE p.stock > 0 ORDER BY p.price ASC")
    List<Product> findAvailableProducts();
}
