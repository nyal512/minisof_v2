package com.nyal.minisof.service.order_detail;

import com.nyal.minisof.model.OrderDetailEntity;
import com.nyal.minisof.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailImpl implements OrderDetailService{
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Override
    public long count() {
        return orderDetailRepository.count();
    }

    @Override
    public void delete(OrderDetailEntity orderDetail) {

    }

    @Override
    public boolean exist(String categoryName) {
        return false;
    }

    @Override
    public OrderDetailEntity save(OrderDetailEntity orderDetail) {
        return null;
    }
}
