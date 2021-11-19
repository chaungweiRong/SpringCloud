package com.atguigu.springcloud.alibaba.service.impl;

import com.atguigu.springcloud.alibaba.dao.PaymentDao;
import com.atguigu.springcloud.alibaba.entities.Payment;
import com.atguigu.springcloud.alibaba.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public int create(Payment payment) {
        Payment payment2 = new Payment();
        long l = Long.parseLong("1");
        payment2.setId(l);
        payment2.setSerial("张三");
        paymentDao.create(payment2);
        Integer.parseInt("hello");//自定义异常
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
