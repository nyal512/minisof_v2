package com.nyal.minisof.controller;

import com.nyal.minisof.model.OrderDetailEntity;
import com.nyal.minisof.service.order_detail.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orderDetail")
public class OrderDetailController {
    @Autowired
    OrderDetailService orderDetailService;
    @GetMapping("/getOrderDetail")
    public ResponseEntity<List<OrderDetailEntity>> getOrderDetailByOrderId(@RequestParam("order_detail_id") Integer orderId){
        if (orderDetailService != null){
            List<OrderDetailEntity> listOrderDetail = orderDetailService.findAllByOrderId(orderId);
            if (listOrderDetail != null){
                return new ResponseEntity<>(listOrderDetail, HttpStatus.OK);
            } else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/addOrderDetail")
    public ResponseEntity<Boolean> addOrderDetail(@RequestBody OrderDetailEntity orderDetail){
        if (orderDetailService != null && orderDetail != null){
            orderDetailService.save(orderDetail);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
    @PutMapping("/updateOrderDetail")
    public ResponseEntity<Boolean> updateOrderDetail(@RequestBody OrderDetailEntity orderDetail){
        if (orderDetailService != null && orderDetail != null){
            OrderDetailEntity existingOrderDetail = orderDetailService.findById(orderDetail.getId()).get();
            if (existingOrderDetail != null){
                existingOrderDetail.setId(orderDetail.getId());
                existingOrderDetail.setOrder(orderDetail.getOrder());
                existingOrderDetail.setProduct(orderDetail.getProduct());
                existingOrderDetail.setQuantity(orderDetail.getQuantity());
                orderDetailService.save(orderDetail);
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/deleteOrderDetail")
    public ResponseEntity<Boolean> deleteOrderDetail(@RequestParam("order_detail_id") Integer orderDetailId){
        if (orderDetailService != null){
            OrderDetailEntity orderDetail = orderDetailService.findById(orderDetailId).get();
            if (orderDetail != null){
                orderDetailService.delete(orderDetail);
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
