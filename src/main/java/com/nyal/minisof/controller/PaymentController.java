package com.nyal.minisof.controller;

import com.nyal.minisof.config.VnPayConfig;
import com.nyal.minisof.dto.PaymentDTO;
import com.nyal.minisof.dto.UrlDetail;
import com.nyal.minisof.service.vnpay.VNPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@Controller
@RequestMapping("api/pay/vnPay")
public class PaymentController {
    @Autowired
    VNPayService vnPayService;
    @PostMapping("/payment")
    public ResponseEntity<?> requestWebVnPay(@RequestBody PaymentDTO payment, HttpServletRequest request){
        String vnpayUrl = vnPayService.createOrder(payment.getAmount(), payment.getDescription());
        UrlDetail url = new UrlDetail(vnpayUrl);
        return new ResponseEntity<>(url,HttpStatus.OK);
    }
}
