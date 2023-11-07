package com.nyal.minisof.controller;

import com.nyal.minisof.model.OrderDetailEntity;
import com.nyal.minisof.model.OrderEntity;
import com.nyal.minisof.service.order_detail.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/orderDetail")
public class OrderDetailController {
    @Autowired
    OrderDetailService orderDetailService;
    @GetMapping("/getOrderDetail")
    public ResponseEntity<OrderEntity> getOrderDetailByOrderId(@RequestParam("order_id") Integer orderId){
        return null;
    }
    @PutMapping("/updateOrderDetail")
    public ResponseEntity<Boolean> updateOrderDetail(@RequestBody OrderDetailEntity orderDetail){
        return null;
    }
    @DeleteMapping("/deleteOrderDetail")
    public ResponseEntity<Boolean> deleteOrderDetail(@RequestParam("order_id") Integer orderId){
        return null;
    }
}
