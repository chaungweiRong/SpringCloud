package com.atguigu.springcloud.alibaba.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage {

    private Long id;

    private Long productId;

    private Integer total; //总库存

    private Integer used; //已用库存

    private Integer residue; //剩余库存
}
