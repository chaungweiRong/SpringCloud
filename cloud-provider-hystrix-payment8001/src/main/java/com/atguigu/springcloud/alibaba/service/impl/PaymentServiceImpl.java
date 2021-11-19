package com.atguigu.springcloud.alibaba.service.impl;

import cn.hutool.core.util.IdUtil;
import com.atguigu.springcloud.alibaba.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池:"+Thread.currentThread().getName()+"paymentInfo_OK,id:"+id;
    }

    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })//commandProperties = {@HystrixProperty(name = "execution.isolation.timeoutInMilliseconds",value = "3000")},给定一个时间峰值，没超出则正常，没超出则异常，调用兜底方法
    public String paymentInfo_TimeOut(Integer id) {
        int timeNumber = 5;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:"+Thread.currentThread().getName()+"paymentInfo_TimeOut,id:"+id+"超时了"+timeNumber+"秒";
    }
    public String paymentInfo_TimeHandler(Integer id) {
        return "线程池:"+Thread.currentThread().getName()+"paymentInfo_TimeHandler,id:"+id+"o(╥﹏╥)o";
    }

    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreak_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期/时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")//失败率达到百分之60后跳闸 十次请求在10000毫秒内达到百分之六十失败（即十次访问六次失败），则启动断路器
    })//参数的选择在HystrixCommandProperties这个类上，全局搜索可以找到
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id<0){
            throw new RuntimeException("id不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+serialNumber;
    }
    public String paymentCircuitBreak_fallback(@PathVariable("id") Integer id){
        return "id不能为负数，请重新输入o(╥﹏╥)o";
    }
}
