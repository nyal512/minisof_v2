package com.nyal.minisof.repository;

import com.nyal.minisof.model.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Integer> {
    @Query(value = "select * from order_detail where order_detail.order_id = ?1", nativeQuery = true)
    public List<OrderDetailEntity> findAllByOrderId(Integer orderId);
}
