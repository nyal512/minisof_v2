package com.nyal.minisof.service.order;

import com.nyal.minisof.model.OrderEntity;
import com.nyal.minisof.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;
    @Override
    public long count() {
        return orderRepository.count();
    }

    @Override
    public boolean exist(Integer orderId) {
        return orderRepository.existsByOrderId(orderId) != 0;
    }

    @Override
    public boolean existById(Integer orderId) {
        return orderRepository.existsById(orderId);
    }

    @Override
    public List<OrderEntity> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderEntity> findAllById(List<Integer> listOrderId) {
        return orderRepository.findAllById(listOrderId);
    }

    @Override
    public Optional<OrderEntity> findById(Integer orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public OrderEntity save(OrderEntity order) {
        return orderRepository.save(order);
    }

    @Override
    public List<OrderEntity> saveAll(List<OrderEntity> listOrder) {
        return orderRepository.saveAll(listOrder);
    }

    @Override
    public List<OrderEntity> getByAccountIdAndStatus(Integer accountId, Integer status) {
        return orderRepository.getByAccountIdAndStatus(accountId, status);
    }

    @Override
    public void updateStatus(Integer status, Integer orderId) {
        orderRepository.updateStatus(status, orderId);
    }
}
