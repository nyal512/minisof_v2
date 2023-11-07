package com.nyal.minisof.controller;

import com.nyal.minisof.model.OrderEntity;
import com.nyal.minisof.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping("/getOrder")
    public ResponseEntity<OrderEntity> getOrderById(@RequestParam("order_id") Integer orderId){
        OrderEntity order = orderService.findById(orderId).get();
        if (order != null){
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listOrder")
    public ResponseEntity<List<OrderEntity>> getAllOrder(){
        if (orderService != null && orderService.findAll() != null){
            List<OrderEntity> listOrder = orderService.findAll();
            return new ResponseEntity<>(listOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/orderByStatus")
    public ResponseEntity<List<OrderEntity>> getAllOrderByStatus(@RequestParam("status") Integer status){
        if (orderService != null && orderService.getAllByStatus(status) != null){
            List<OrderEntity> listOrder = orderService.getAllByStatus(status);
            return new ResponseEntity<>(listOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getByAccountIdAndStatus")
    public ResponseEntity<List<OrderEntity>> getByAccountIdAndStatus(@RequestParam("account_id") Integer accountId, @RequestParam("status") Integer status){
        if (orderService != null && orderService.getByAccountIdAndStatus(accountId, status) != null){
            List<OrderEntity> listOrder = orderService.getByAccountIdAndStatus(accountId, status);
            return new ResponseEntity<>(listOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/addOrder")
    public ResponseEntity<Boolean> addOrder(@RequestBody OrderEntity order){
        if (orderService != null && order != null && !orderService.existById(order.getOrderId())){
            orderService.save(order);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/updateStatus")
    public ResponseEntity<Boolean> updateOrder(@RequestParam("order_id") Integer orderId, @RequestParam("status") Integer status){
        if (orderService != null){
            orderService.updateStatus(status, orderId);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
