package com.czxy.changgou3.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author zhandehuang@itcast.cn
 * @version 1.0
 * @date 2020/4/21 0021
 **/
@Table(name = "tb_sku_photo")
@Data
public class SkuPhoto implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    //外键
    @Column(name="sku_id")
    //@JsonProperty("sku_id")
    private Integer skuId;
    @Transient
    private Sku sku;

    @Column(name="url")
    private String url;
}
