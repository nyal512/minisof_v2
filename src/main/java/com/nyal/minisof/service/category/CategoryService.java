package com.nyal.minisof.service.category;

import com.nyal.minisof.model.CategoryEntity;
import com.nyal.minisof.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    long count();
    void delete(CategoryEntity category);
    void deleteAll();
    void deleteAll(List<CategoryEntity> listCategory);
    void deleterAllById(List<Integer> listCategoryId);
    void deleteById(Integer categoryId);
    boolean exist(String categoryName);
    boolean existById(Integer categoryId);
    List<CategoryEntity> findAll();
    List<CategoryEntity> findAllById(List<Integer> listCategoryId);
    Optional<CategoryEntity> findById(Integer categoryId);
    CategoryEntity findByName(String categoryName);
    CategoryEntity save(CategoryEntity category);
    List<CategoryEntity> saveAll(List<CategoryEntity> listCategory);
}
