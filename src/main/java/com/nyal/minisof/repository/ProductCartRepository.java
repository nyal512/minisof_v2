package com.nyal.minisof.repository;

import com.nyal.minisof.model.ProductCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCartRepository extends JpaRepository<ProductCartEntity, Integer> {

}
