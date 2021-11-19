package com.atguigu.springcloud.alibaba.service.impl;

import com.atguigu.springcloud.alibaba.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackServiceImpl implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "o(╥﹏╥)o";
    }
}
