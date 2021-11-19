package com.atguigu.springcloud.alibaba.service;

import java.math.BigDecimal;

public interface AccountService {
    //扣减余额
    void decrease(Long userId, BigDecimal money);
}
