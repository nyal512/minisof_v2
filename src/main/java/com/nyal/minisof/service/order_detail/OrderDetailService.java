package com.nyal.minisof.service.order_detail;


import com.nyal.minisof.model.OrderDetailEntity;

import java.util.List;
import java.util.Optional;

public interface OrderDetailService {
    long count();
    void delete(OrderDetailEntity orderDetail);
    boolean exist(String categoryName);
    OrderDetailEntity save(OrderDetailEntity orderDetail);
}
