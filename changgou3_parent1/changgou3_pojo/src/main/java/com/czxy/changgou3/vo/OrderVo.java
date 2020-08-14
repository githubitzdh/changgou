package com.czxy.changgou3.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

/**
 * @author zhandehuang@itcast.cn
 *•用于封装请求数据（收货人信息、送货方式、支付方式、发票信息）
 * @date 2020/5/4 0004
 **/
@Data
public class OrderVo {
    //收货人地址ID
    @JsonProperty("address_id")
    private Integer addressId;

    //送货方式
    @JsonProperty("post_method")
    private Integer postMethod;

    //支付方式
    @JsonProperty("pay_method")
    private Integer payMethod;

    //发票
    private Map<Object,Object> invoice;
}
