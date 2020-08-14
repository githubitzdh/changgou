package com.czxy.changgou3.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
将数据库中数据,转到es中,ESData用于封装es的数据
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ESData {
    private Integer id; // skuId
    private String logo;//图片地址
    private String skuName;//sku名字
    private String all; // 所有需要被搜索的信息，包含标题，分类，甚至品牌
    private Date onSaleTime;//上架时间
    //品牌编号
    private Integer brandId;
    //分类id
    private Integer catId;
    //规格列表
    private Map<String, Object> specs;// 可搜索的规格参数，key是参数名，值是参数值
    private Double price;// 价格
    private String spuName;
    private Integer stock;
    private String description;
    private String packages;//规格与包装
    private String aftersale;//售后保障
    private String midlogo;
    //评价数
    private Integer commentCount;
    // 销量
    private Integer sellerCount;
}
