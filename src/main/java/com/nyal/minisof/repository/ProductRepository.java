package com.nyal.minisof.repository;

import com.nyal.minisof.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{
    @Query(value = "select * from product where product.status = 1", nativeQuery = true)
    public List<ProductEntity> findAll();
    @Query(value = "select product from ProductEntity product where product.name like ?1 and product.status = 1", nativeQuery = true)
    public List<ProductEntity> findAllByName(String name);
    @Query(value = "select * from product where product.category_id = ?1 and product.status = 1", nativeQuery = true)
    public List<ProductEntity> findAllByCategory(Integer Category_id);

    @Query(value = "SELECT * FROM product WHERE product.status = 1 ORDER BY id DESC LIMIT 6;", nativeQuery = true)
    public List<ProductEntity> getTop6Products();

    @Query(value = "select * from product  where product.category_id = 1 and product.status = 1 order by id desc limit 6", nativeQuery = true)
    public List<ProductEntity> getTop6Phones();

    @Query(value = "select  * from product  where product.category_id = 2 and product.status = 1 order by id desc limit 6", nativeQuery = true)
    public List<ProductEntity> getTop6Tablets();

    @Query(value = "select * from product  where product.category_id = 3 and product.status = 1 order by id desc limit 6", nativeQuery = true)
    public List<ProductEntity> getTop6Laptops();

    @Query(value = "select * from product  where product.category_id = 4 and product.status = 1 order by id desc limit 6", nativeQuery = true)
    public List<ProductEntity> getTop6Accessories();
}
