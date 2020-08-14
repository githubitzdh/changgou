package com.czxy.changgou3.vo;

import lombok.Data;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/1 0001
 **/
@Data
public class PageRequest {
   //常见字段
    private Integer pageNum;    //当前页
    private Integer pageSize;   //每页条数
    private String sortBy;     //排序字段
    private String sortWay;    //排序方式(asc | desc)
    //暂时不使用,可以删除
    private Integer limit;      //限制条数
    private Integer offset;     //偏移

}
