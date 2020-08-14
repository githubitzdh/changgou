package com.czxy.changgou3.vo;

import lombok.Data;

import java.util.Map;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/16 0016
 **/
@Data
public class SearchVo  extends PageRequest{
//此类用于封装查询条件
    private Integer catId;                  // 3 级类目
    private Integer brandId;                // 品牌
    private Map<String,String> specList;    // 规格选项列表
    private Double minPrice;                //最低价格
    private Double maxPrice;                //最高价格
    //private Integer limit;                  //限制条数
    private String keyword;                 // 关键字搜索，预留
}
