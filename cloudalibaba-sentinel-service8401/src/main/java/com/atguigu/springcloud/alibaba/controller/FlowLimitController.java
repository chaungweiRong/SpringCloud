package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.alibaba.myHandler.CustomerBlockHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        return "testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "testB";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandlerClass = CustomerBlockHandler.class,blockHandler = "handlerException")
    public String testHotKey(@RequestParam(value = "p1" , required = false) String p1,@RequestParam(value = "p2",required = false) String p2){
        return "testHotKey";
    }

    /**
     *
     * 兜底方法
     * @param p1
     * @param p2
     * @param blockException
     * @return
     */
    public String deal_testHotKey(String p1, String p2, BlockException blockException){
        return "testHotKey,o(╥﹏╥)o";
    }



}

