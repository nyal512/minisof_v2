package com.nyal.minisof.controller;

import com.nyal.minisof.model.CategoryEntity;
import com.nyal.minisof.model.ProductEntity;
import com.nyal.minisof.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final static int PRODUCT_NEW = 1;
    @Autowired
    ProductService productService;
    @GetMapping("/listProduct")
    public ResponseEntity<List<ProductEntity>> getAllProduct(){
        List<ProductEntity> listProduct = new ArrayList<>();
        if (productService.findAll() != null){
            listProduct.addAll(productService.findAll());
        }
        return new ResponseEntity<>(listProduct, HttpStatus.OK);
    }
    @GetMapping("/getProduct")
    public ResponseEntity<ProductEntity> getProduct(@RequestParam("product_id") Integer productId){
        ProductEntity product = productService.findById(productId).get();
        if (product != null){
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getProductByName")
    public ResponseEntity<ProductEntity> getProductByName(@RequestParam("product_name") String productName){
        ProductEntity product = productService.findByName(productName);
        if (product != null){
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/top6NewProduct")
    public ResponseEntity<List<ProductEntity>> getTop6NewProduct(){
        List<ProductEntity> listProductNew = productService.getTop6NewProducts();
        if (listProductNew != null){
            return new ResponseEntity<>(listProductNew, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/top6ProductByCategory")
    public ResponseEntity<List<ProductEntity>> getTop6ProductByCategory(@RequestParam("category_id") Integer categoryId){
        List<ProductEntity> listProductByCategory = productService.getTop6ProductByCategory(categoryId);
        if (listProductByCategory != null){
            return new ResponseEntity<>(listProductByCategory, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listNewProduct")
    public ResponseEntity<List<ProductEntity>> getAllNewProduct(){
        List<ProductEntity> listProductNew = productService.findAllNewProduct();
        if (listProductNew != null){
            return new ResponseEntity<>(listProductNew, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listProductByCategory")
    public ResponseEntity<List<ProductEntity>> getAllProductByCategory(@RequestParam("category_id") Integer categoryId){
        List<ProductEntity> listProductByCategory = productService.findAllByCategory(categoryId);
        if (listProductByCategory != null){
            return new ResponseEntity<>(listProductByCategory, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listProductByKeySearch")
    public ResponseEntity<List<ProductEntity>> findAllByKeySearch(@RequestParam("name") String key){
        List<ProductEntity> listProductByKeySearch = productService.findAllByName(key);
        if (listProductByKeySearch != null){
            return new ResponseEntity<>(listProductByKeySearch, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/addProduct")
    public ResponseEntity<Boolean> addProduct(@RequestBody ProductEntity product){
        if (product != null && !productService.existById(product.getProductId())){
            product.setCreated(new Date());
            product.setStatus(PRODUCT_NEW);
            productService.save(product);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @PutMapping("/editProduct")
    public ResponseEntity<Boolean> editProduct(@RequestBody ProductEntity product){
        ProductEntity existingProduct = productService.findById(product.getProductId()).get();
        if (existingProduct != null) {
            existingProduct.setProductId(product.getProductId());
            existingProduct.setName(product.getName());
            existingProduct.setDescriptions(product.getDescriptions());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setImage(product.getImage());
            productService.save(existingProduct);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteProduct(@RequestParam("product_id") Integer product_id) {
        ProductEntity existingProduct = productService.findById(product_id).get();
        if (existingProduct != null) {
            productService.delete(existingProduct);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}
