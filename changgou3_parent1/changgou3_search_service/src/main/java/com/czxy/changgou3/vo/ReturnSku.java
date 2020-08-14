package com.czxy.changgou3.vo;

import lombok.Data;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0

 **/
@Data
public class ReturnSku {
    //对searchsku简化,需要啥拿啥过来即可
    private Integer id;
    private String goodsName;           //sku名称
    private Double price;               //价格
    private String midlogo;             //sku logo
    private Integer commentCount;      //sku的评论
}
