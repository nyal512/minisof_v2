package com.nyal.minisof.service.order;

import com.nyal.minisof.model.CategoryEntity;
import com.nyal.minisof.model.OrderEntity;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    long count();
    boolean exist(Integer orderId);
    boolean existById(Integer orderId);
    List<OrderEntity> findAll();
    List<OrderEntity> findAllById(List<Integer> listOrderId);
    Optional<OrderEntity> findById(Integer orderId);
    OrderEntity save(OrderEntity order);
    List<OrderEntity> saveAll(List<OrderEntity> listOrder);
    List<OrderEntity> getByAccountIdAndStatus(Integer accountId, Integer status);
    void updateStatus(Integer status, Integer orderId);
}
