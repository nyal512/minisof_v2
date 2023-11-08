package com.nyal.minisof.service.order_detail;


import com.nyal.minisof.model.OrderDetailEntity;

import java.util.List;
import java.util.Optional;

public interface OrderDetailService {
    long count();
    void delete(OrderDetailEntity orderDetail);
    boolean existByOrderId(Integer orderId);
    Optional<OrderDetailEntity> findById(Integer orderDetailId);
    List<OrderDetailEntity> findAllByOrderId(Integer orderId);
    OrderDetailEntity save(OrderDetailEntity orderDetail);
}
