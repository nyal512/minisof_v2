package com.nyal.minisof.repository;

import com.nyal.minisof.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{
    @Query(value = "select * from `product_entity`", nativeQuery = true)
    public List<ProductEntity> findAll();
    @Query(value = "select * from `product_entity` where name = ?1", nativeQuery = true)
    public ProductEntity findByName(String name);
    @Query(value = "SELECT * FROM `product_entity` WHERE name LIKE %?1%", nativeQuery = true)
    public List<ProductEntity> findAllByName(String key);
    @Query(value = "select * from `product_entity` where category_id = ?1", nativeQuery = true)
    public List<ProductEntity> findAllByCategory(Integer category_id);
    @Query(value = "select * from `product_entity` where status = 1", nativeQuery = true)
    public List<ProductEntity> findAllNewProduct();
    @Query(value = "SELECT * FROM `product_entity` WHERE status = 1 ORDER BY product_id DESC LIMIT 6;", nativeQuery = true)
    public List<ProductEntity> getTop6NewProducts();

    @Query(value = "select * from `product_entity`  where category_id = ?1 order by product_id desc limit 6", nativeQuery = true)
    public List<ProductEntity> getTop6ProductByCategoryId(Integer category_id);
    @Query(value = "SELECT COUNT(*) FROM `product_entity` WHERE product_id = ?1", nativeQuery = true)
    int existsByProductId(Integer productId);
}
