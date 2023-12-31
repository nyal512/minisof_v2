package com.nyal.minisof.service.product_cart;

import com.nyal.minisof.model.CategoryEntity;
import com.nyal.minisof.model.ProductCartEntity;

import java.util.List;
import java.util.Optional;

public interface ProductCartService {
    long count();
    void delete(int productCartId);
    void deleteAll();
    void deleteAll(List<ProductCartEntity> listProductCart);
    boolean existByProductId(Integer productId);
    List<ProductCartEntity> findAll();
    Optional<ProductCartEntity> findById(Integer productCartId);
    ProductCartEntity findByProductId(int productId);
    ProductCartEntity save(ProductCartEntity productCart);
    List<ProductCartEntity> saveAll(List<ProductCartEntity> listProductCart);
    List<ProductCartEntity> getAllProductCartByAccountId(int accountId);
}
