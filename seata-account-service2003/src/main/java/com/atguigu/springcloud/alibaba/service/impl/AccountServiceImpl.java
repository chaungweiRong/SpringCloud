package com.atguigu.springcloud.alibaba.service.impl;

import com.atguigu.springcloud.alibaba.dao.AccountDao;
import com.atguigu.springcloud.alibaba.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        log.info("----->account-service开始扣减账户余额");
        /*模拟超时已超，由于使用的是OpenFeign远程调用服务，其默认超时时间为一秒，
        此时过了一秒后还没处理完，此处将会报异常，订单状态修改失败，即前面的业务即使付款成功，
        订单状态却是未完成的*/
        //解决办法，在业务的最初入口添加@GlobalTransactional注解，即order业务类上
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        accountDao.decrease(userId, money);
        log.info("----->account-service完成扣减");

    }
}
