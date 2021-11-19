package com.atguigu.springcloud.alibaba.service;

import org.springframework.web.bind.annotation.PathVariable;

public interface PaymentService {
    /**
     * 正常访问，能够成功
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id);

    /**\
     * 正常访问，遇到超时异常
     * @param id
     * @return
     */
    public String paymentInfo_TimeOut(Integer id);

    /**
     * 服务熔断测试
     * @param id
     * @return
     */
    public String paymentCircuitBreaker(Integer id);
}
