package com.atguigu.springcloud.alibaba.controller;

import com.atguigu.springcloud.alibaba.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendMessageController {
    @Resource
    private IMessageProvider messageProvider;

    @GetMapping(value = "/senMessage")
    public String send(){
        return messageProvider.send();
    }
}
