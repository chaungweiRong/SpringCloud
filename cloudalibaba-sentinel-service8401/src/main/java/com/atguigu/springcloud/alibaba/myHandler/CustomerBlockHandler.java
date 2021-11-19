package com.atguigu.springcloud.alibaba.myHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.alibaba.entities.CommonResult;

public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException blockException){
        return new CommonResult(444,"自定义处理");
    }
    public static CommonResult handlerException2(BlockException blockException){
        return new CommonResult(300,"自定义处理");
    }

}
