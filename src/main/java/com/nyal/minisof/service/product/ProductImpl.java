package com.nyal.minisof.service.product;

import com.nyal.minisof.model.ProductEntity;
import com.nyal.minisof.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;
    @Override
    public long count() {
        return productRepository.count();
    }

    @Override
    public void delete(ProductEntity product) {
        productRepository.delete(product);
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }

    @Override
    public void deleteAll(List<ProductEntity> listProduct) {
        productRepository.deleteAll(listProduct);
    }

    @Override
    public void deleterAllById(List<Integer> listProductId) {
        productRepository.deleteAllById(listProductId);
    }

    @Override
    public void deleteById(Integer productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public boolean exist(Integer productId) {
        return productRepository.existsByProductId(productId)!=0;
    }

    @Override
    public boolean existById(Integer productId) {
        return productRepository.existsById(productId);
    }

    @Override
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductEntity> findAllById(List<Integer> listProductId) {
        return productRepository.findAllById(listProductId);
    }

    @Override
    public Optional<ProductEntity> findById(Integer productId) {
        return productRepository.findById(productId);
    }

    @Override
    public ProductEntity save(ProductEntity product) {
        return productRepository.save(product);
    }

    @Override
    public List<ProductEntity> saveAll(List<ProductEntity> listProduct) {
        return productRepository.saveAll(listProduct);
    }

    @Override
    public ProductEntity findByName(String productName) {
        return productRepository.findByName(productName);
    }

    @Override
    public List<ProductEntity> findAllByCategory(Integer category_id) {
        return productRepository.findAllByCategory(category_id);
    }

    @Override
    public List<ProductEntity> findAllNewProduct() {
        return productRepository.findAllNewProduct();
    }

    @Override
    public List<ProductEntity> getTop6NewProducts() {
        return productRepository.getTop6NewProducts();
    }

    @Override
    public List<ProductEntity> getTop6ProductByCategory(Integer categoryId) {
        return productRepository.getTop6ProductByCategoryId(categoryId);
    }

    @Override
    public List<ProductEntity> findAllByName(String name) {
        return productRepository.findAllByName(name);
    }
}
