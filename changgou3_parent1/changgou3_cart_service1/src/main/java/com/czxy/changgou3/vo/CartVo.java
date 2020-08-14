package com.czxy.changgou3.vo;

import lombok.Data;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/27 0027
 **/
@Data
public class CartVo {
    private Integer skuid ;     //"SKUID",
    private Integer count;      //"购买数量"
    private Boolean checked;    //是否选中
}
