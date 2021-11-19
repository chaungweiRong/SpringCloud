package com.atguigu.springcloud.alibaba.controller;

import com.atguigu.springcloud.alibaba.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallBack")
public class PaymentHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String resule = paymentHystrixService.paymentInfo_OK(id);
        return resule;
    }

    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeHandler",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })//commandProperties = {@HystrixProperty(name = "execution.isolation.timeoutInMilliseconds",value = "3000")},给定一个时间峰值，没超出则正常，没超出则异常，调用兜底方法
    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        //int age = 10/0;模拟运行错误
        String resule = paymentHystrixService.paymentInfo_TimeOut(id);
        return resule;
    }
    public String paymentInfo_TimeHandler(Integer id) {
        return "线程池:"+Thread.currentThread().getName()+"消费者80：paymentInfo_TimeHandler,id:"+id+"o(╥﹏╥)o";
    }

    public String payment_Global_FallBack(){
        return "线程池:"+Thread.currentThread().getName()+"消费者80：超时或运行错误+o(╥﹏╥)o";
    }


}
