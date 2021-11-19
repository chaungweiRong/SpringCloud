package com.atguigu.springcloud.alibaba.service.impl;

import com.atguigu.springcloud.alibaba.dao.OrderDao;
import com.atguigu.springcloud.alibaba.domain.Order;
import com.atguigu.springcloud.alibaba.service.AccountService;
import com.atguigu.springcloud.alibaba.service.OrderService;
import com.atguigu.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    @Override
    //毫秒级，遇到异常可回滚，可提供兜底方法
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)//name是唯一的，rollbackFor = Exception.class遇到任意异常都要回滚
    public void createOrder(Order order) {
        log.info("------>开始新建订单");
        orderDao.createOrder(order);
        log.info("------>订单微服务开始调用库存，进行扣减");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("------>订单微服务开始调用库存，扣减成功");
        log.info("------>订单微服务开始调用账户，进行金额扣减");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("------>订单微服务开始调用账户，扣减成功");
        log.info("------>开始修改订单状态");
        orderDao.updateOrderStatus(order.getUserId(),0);
        log.info("------>end");
    }
}
