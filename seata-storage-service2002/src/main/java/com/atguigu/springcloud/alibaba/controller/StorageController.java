package com.atguigu.springcloud.alibaba.controller;

import com.atguigu.springcloud.alibaba.entities.CommonResult;
import com.atguigu.springcloud.alibaba.service.StorageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class StorageController {

    @Resource
    private StorageService storageService;

    @RequestMapping(value = "/storage/decrease",method = RequestMethod.POST)
    public CommonResult decrease(Long productId, Integer count){
        storageService.decrease(productId, count);
        return new CommonResult(200,"扣减成功");
    }
}
