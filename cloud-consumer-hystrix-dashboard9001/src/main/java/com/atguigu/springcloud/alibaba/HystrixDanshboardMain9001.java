package com.atguigu.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDanshboardMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDanshboardMain9001.class,args);
    }
}
