package com.nyal.minisof.service.product_cart;

import com.nyal.minisof.model.ProductCartEntity;
import com.nyal.minisof.repository.ProductCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCartImpl implements ProductCartService{
    @Autowired
    ProductCartRepository productCartRepository;

    @Override
    public long count() {
        return productCartRepository.count();
    }

    @Override
    public void delete(ProductCartEntity productCart) {
        productCartRepository.delete(productCart);
    }

    @Override
    public void deleteAll() {
        productCartRepository.deleteAll();
    }

    @Override
    public void deleteAll(List<ProductCartEntity> listProductCart) {
        productCartRepository.deleteAll(listProductCart);
    }

    @Override
    public boolean existByProductId(Integer productId) {
        return productCartRepository.existsByProductId(productId)!=0;
    }

    @Override
    public List<ProductCartEntity> findAll() {
        return productCartRepository.findAll();
    }

    @Override
    public Optional<ProductCartEntity> findById(Integer productCartId) {
        return productCartRepository.findById(productCartId);
    }

    @Override
    public ProductCartEntity findByProductId(int productId) {
        return productCartRepository.findByProductId(productId);
    }

    @Override
    public ProductCartEntity save(ProductCartEntity productCart) {
        return productCartRepository.save(productCart);
    }

    @Override
    public List<ProductCartEntity> saveAll(List<ProductCartEntity> listProductCart) {
        return productCartRepository.saveAll(listProductCart);
    }
}
