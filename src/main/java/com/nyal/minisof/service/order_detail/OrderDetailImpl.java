package com.nyal.minisof.service.order_detail;

import com.nyal.minisof.model.OrderDetailEntity;
import com.nyal.minisof.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        orderDetailRepository.delete(orderDetail);
    }

    @Override
    public boolean existByOrderId(Integer orderId) {
        return orderDetailRepository.existsByOrderId(orderId)!=0;
    }

    @Override
    public Optional<OrderDetailEntity> findById(Integer orderDetailId) {
        return orderDetailRepository.findById(orderDetailId);
    }

    @Override
    public List<OrderDetailEntity> findAllByOrderId(Integer orderId) {
        return orderDetailRepository.findAllByOrderId(orderId);
    }

    @Override
    public OrderDetailEntity save(OrderDetailEntity orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }
}
