package com.nyal.minisof.repository;

import com.nyal.minisof.model.ProductCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductCartRepository extends JpaRepository<ProductCartEntity, Integer> {
    @Query(value = "SELECT COUNT(*) FROM `product_cart_entity` WHERE product_id = ?1", nativeQuery = true)
    int existsByProductId(Integer productId);
    @Query(value = "SELECT * FROM `product_cart_entity` WHERE product_id= ?1", nativeQuery = true)
    ProductCartEntity findByProductId(int productId);
    @Query(value = "SELECT * FROM `product_cart_entity` WHERE account_id= ?1", nativeQuery = true)
    List<ProductCartEntity> getAllProductCartByAccountId(int accountId);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ProductCartEntity pce where pce.productCartId = :productCartId")
    void deleteProductCartById(int productCartId);
}
