package com.nyal.minisof.service.product;

import com.nyal.minisof.model.AccountEntity;
import com.nyal.minisof.model.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    long count();

    void delete(ProductEntity product);

    void deleteAll();

    void deleteAll(List<ProductEntity> listProduct);

    void deleterAllById(List<Integer> listProductId);

    void deleteById(Integer productId);

    boolean exist(Integer productId);

    boolean existById(Integer productId);

    List<ProductEntity> findAll();

    List<ProductEntity> findAllByName(String name);

    List<ProductEntity> findAllById(List<Integer> listProductId);

    Optional<ProductEntity> findById(Integer productId);

    ProductEntity save(ProductEntity product);

    List<ProductEntity> saveAll(List<ProductEntity> listProduct);

    List<ProductEntity> findAllByCategory(Integer category_id);

    List<ProductEntity> findAllNewProduct();

    List<ProductEntity> getTop6NewProducts();

    List<ProductEntity> getTop6ProductByCategory(Integer category_id);
}
