package com.nyal.minisof.repository;

import com.nyal.minisof.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    @Query(value = "SELECT COUNT(*) FROM `category_entity` WHERE category_name = ?1", nativeQuery = true)
    int existsByCategoryName(String category_name);
    @Query(value = "SELECT * FROM `category_entity` WHERE category_name = ?1", nativeQuery = true)
    CategoryEntity findByCategoryName(String category_name);
}
