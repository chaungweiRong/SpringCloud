package com.atguigu.springcloud.alibaba.service.impl;

import cn.hutool.core.lang.UUID;
import com.atguigu.springcloud.alibaba.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;

/**
 * Source.class定义消息的推送管道
 */
@EnableBinding(Source.class)
@Slf4j
public class IMessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output; //消息发送通道

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());//MessageBuilder消息构建其
        log.info("发送："+serial);
        return serial;
    }
}
