package com.nyal.minisof.service.vnpay;

import javax.servlet.http.HttpServletRequest;

public interface VNPayService {
    String createOrder(int total, String orderInfo);
    boolean orderReturn(HttpServletRequest request);
}
