package com.nyal.minisof.service.category;

import com.nyal.minisof.model.CategoryEntity;
import com.nyal.minisof.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryImpl implements CategoryService{
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public long count() {
        return categoryRepository.count();
    }

    @Override
    public void delete(CategoryEntity category) {
        categoryRepository.delete(category);
    }

    @Override
    public void deleteAll() {
        categoryRepository.deleteAll();
    }

    @Override
    public void deleteAll(List<CategoryEntity> listCategory) {
        categoryRepository.deleteAll(listCategory);
    }

    @Override
    public void deleterAllById(List<Integer> listCategoryId) {
        categoryRepository.deleteAllById(listCategoryId);
    }

    @Override
    public void deleteById(Integer categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public boolean exist(String categoryName) {
        return categoryRepository.existsByCategoryName(categoryName)!= 0;
    }

    @Override
    public boolean existById(Integer categoryId) {
        return categoryRepository.existsById(categoryId);
    }

    @Override
    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<CategoryEntity> findAllById(List<Integer> listCategoryId) {
        return categoryRepository.findAllById(listCategoryId);
    }

    @Override
    public CategoryEntity findByName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

    @Override
    public Optional<CategoryEntity> findById(Integer categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public CategoryEntity save(CategoryEntity category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<CategoryEntity> saveAll(List<CategoryEntity> listCategory) {
        return categoryRepository.saveAll(listCategory);
    }

}
