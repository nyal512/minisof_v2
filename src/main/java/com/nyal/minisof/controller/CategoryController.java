package com.nyal.minisof.controller;

import com.nyal.minisof.model.AccountEntity;
import com.nyal.minisof.model.CategoryEntity;
import com.nyal.minisof.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController{
    @Autowired
    CategoryService categoryService;
    @GetMapping("/getCategory")
    public ResponseEntity<CategoryEntity> getCategory(@RequestParam("category_id") int categoryId) {
        CategoryEntity category = categoryService.findById(categoryId).get();
        if (category != null) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getCategoryByName")
    public ResponseEntity<CategoryEntity> getCategoryByName(@RequestParam("category_name") String categoryName) {
        CategoryEntity category = categoryService.findByName(categoryName);
        if (category != null) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getAllCategory")
    public ResponseEntity<List<CategoryEntity>> getAllCategory() {
        List<CategoryEntity> listCategory = categoryService.findAll();
        if (listCategory != null) {
            return new ResponseEntity<>(listCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/addCategory")
    public ResponseEntity<Boolean> addCategory(@RequestBody CategoryEntity category) {
        if (!categoryService.exist(category.getCategoryName())) {
            categoryService.save(category);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/editCategory")
    public ResponseEntity<Boolean> editAccount(@RequestBody CategoryEntity category) {
        CategoryEntity existingCategory = categoryService.findById(category.getCategoryId()).get();
        if (existingCategory != null) {
            existingCategory.setCategoryId(category.getCategoryId());
            existingCategory.setCategoryName(category.getCategoryName());
            existingCategory.setProducts(category.getProducts());
            categoryService.save(existingCategory);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteAccount(@RequestParam("category_id") int categoryId) {
        CategoryEntity existingCategory = categoryService.findById(categoryId).get();
        if (existingCategory != null) {
            categoryService.delete(existingCategory);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
